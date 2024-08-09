
@SuppressWarnings("unused")
public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(Integer value) {
        Node newNode = new Node(value);

        if (root == null) {
            this.root = newNode;
        } else {
            Node current = this.root; // nó atual referencia raiz
            Node dad = null; // nó pai o qual sera utilizado para linkar o próximo nó
            boolean isLeft = false; // para determinar de qual lado está a exploração

            while (current != null) { // permite a exploração de forma correta
                if(newNode.getValue() < current.getValue()){ // menor à esquerda
                    dad = current;
                    current = current.getLeft();
                    isLeft = true;
                } else { // maior à direita
                    dad = current;
                    current = current.getRight();
                    isLeft = false;
                }
            }

            if(isLeft){
                dad.setLeft(newNode);
            } else{
                dad.setRight(newNode);
            }
        }
    }

    public void preOrder(Node root){
        if(root == null){
            return;
        }

        System.out.println(root.getValue());

        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void inOrder(Node root){
        if(root == null){
            return;
        }

        inOrder(root.getLeft());

        System.out.println(root.getValue());

        inOrder(root.getRight());
    }

    public void posOrder(Node root){
        if(root == null){
            return;
        }

        posOrder(root.getLeft());
        posOrder(root.getRight());

        System.out.println(root.getValue());
    }

    public void remove(Integer value){
        Node current = this.root; // Para percorrer a mesma lógica usada na inserção
        Node dad = null;
        boolean isLeft = false;

        // caso de árvore vazia
        if(root == null){
            System.out.println("Árvore vazia");
            return;
        }

        // percorre a árvore até encontrar o valor correto
        while (current.getValue() != value) {
            if(value < current.getValue()){ // menor à esquerda
                dad = current;
                current = current.getLeft();
                isLeft = true;
            } else { // maior à direita
                dad = current;
                current = current.getRight();
                isLeft = false;
            }
        }

        // Os casos em que dad é igul a null, significam que estamos na raiz
        // portanto, simplesmente setamos seu valor como null, ou como seu
        // filho imediado à esquerda ou à direita

        // caso 01: nó folha
        if(current.getLeft() == null && current.getRight() == null){
            if(dad == null){
                current.setValue(null); // RAIZ
                return;
            }

            if(isLeft){
                dad.setLeft(null);
            } else{
                dad.setRight(null);
            }
            return; // Nó folha, apenas seta para seu pai, ou à esquerda ou à direita como nulo
        }

        // caso 02: filho à esquerda
        if(current.getLeft() != null && current.getRight() == null){
            if(dad == null){
                current.setValue(current.getLeft().getValue()); // Raiz agora tem o valor de seu filho
                current.setLeft(null); // Elimina o filho o qual está na raiz agora
                return;
            }

            if(isLeft){
                dad.setLeft(current.getLeft());
            } else {
                dad.setRight(current.getLeft());
            }
            return;
        }

        // caso 03: filho à direita
        if(current.getRight() != null && current.getLeft() == null){
            if(dad == null){
                current.setValue(current.getRight().getValue());
                current.setRight(null);
                return;
            }

            if(!isLeft){
                dad.setRight(current.getRight());
            } else {
                dad.setLeft(current.getRight());
            }
            return;
        }

        // caso 04: dois filhos
        if(current.getRight() != null && current.getLeft() != null){
            Node newCurrent = current.getRight(); // usados para percorrer e achar o menor filho
            Node before = newCurrent;             // da sub-árvore direita

            // Caso em que a raiz a ser removida só possui um filho em sua sub-árvore direita
            if(newCurrent.getLeft() == null && newCurrent.getRight() == null){
                current.setValue(newCurrent.getValue());
                current.setRight(null);
                return;
            }

            while(newCurrent.getLeft() != null){
                before = newCurrent;
                newCurrent = newCurrent.getLeft();
            }

            current.setValue(newCurrent.getValue()); // seta o valor do nó que queremos eliminar

            if(newCurrent.getRight() != null){
                before.setLeft(newCurrent.getRight()); // Se o menor filho tiver filhos a direita passa para seu pai à esquerda
            } else {
                before.setLeft(null); // Caso contrário, define como nulo
            }
        }
    }
}
