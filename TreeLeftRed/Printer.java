class Tree{
    Node root;
    
    class Node{
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color{
        BLACK,
        RED
    }

    public void insert(int v){
        if(root != null){
            insert(root,v);
            root = balance(root);
        }else{
            root = new Node();
            root.value = v;
        }
        root.color = Color.BLACK;
        
    }
    
    public void insert(Node n,int v){
            if(n.value != v){
                if(n.value < v){
                    if(n.right != null){
                        insert(n.right,v);
                        n.right = balance(n.right);
                    }else{
                        n.right = new Node();
                        n.right.value = v;
                        n.right.color = Color.RED;
                    }
                }else{
                    if(n.left != null){
                        insert(n.left,v);
                        n.left = balance(n.left);
                    }else{
                        n.left = new Node();
                        n.left.value = v;
                        n.left.color = Color.RED;
                    }
                }
            }
    }

    public Node find(int v){
        return find(root, v);
    }

    private Node find(Node n, int v){
        if(n == null){
            return null;
        }
        if(n.value == v){
            return n;
        }
        if(n.value < v){
            return find(n.right, v);
        }else{
            return find(n.left, v);
        }
    }

    private Node rotateLeft(Node n){
        Node cur = n.right;
        n.right = cur.left;
        cur.left = n;
        cur.color = n.color;
        n.color = Color.RED;
        return cur;
    }

    private Node rotateRight(Node n){
        Node cur = n.left;
        n.left = cur.right;
        cur.right = n;
        cur.color = n.color;
        n.color = Color.RED;
        return cur;
    }

    private void flipColors(Node n){
        n.color = (n.color == Color.RED ? Color.BLACK: Color.RED);
        n.left.color = (n.left.color == Color.RED ? Color.BLACK: Color.RED);
        n.right.color = (n.right.color == Color.RED ? Color.BLACK: Color.RED);
    }

    private Node balance(Node n){
        Node cur = n;
        boolean flag = true;
        do {
            flag = false;
            if(cur.right != null && cur.right.color == Color.RED && (cur.left == null || cur.left.color == Color.BLACK)){
                flag = true;
                cur = rotateLeft(cur);
            }
            if(cur.left != null && cur.left.color == Color.RED && cur.left.left != null && cur.left.left.color == Color.RED){
                flag = true;
                cur = rotateRight(cur);
            }
            if(cur.left != null && cur.left.color == Color.RED && cur.right != null && cur.right.color == Color.RED){
                flag = true;
                flipColors(cur);
            }
            
        } while (flag);
        return cur;
    }

    public void print(){
        print(root,0);
    }

    private void print(Node n, int deep){
        if(n == null){
            return;
        }
        print(n.left,deep+4);
        for (int i = 0; i < deep; i++) {
            System.out.print(" ");
        }
        System.out.println("value:  " + n.value + " {color: " + n.color + "}");

        print(n.right,deep+4);
    }
}

public class Printer {
    public static void main(String[] args) {
        Tree t = new Tree();
        for (int i = 0; i < 20; i++) {
            t.insert(i);
        }
        t.print();
    }
}