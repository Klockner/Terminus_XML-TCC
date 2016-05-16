package Program;

//Esta classe aqui é para ser substituida pelo programa em XML que você fizer.
//Para padronizarmos a programação eu vou colocar aqui a forma como vamos implementar
//Todos os comentários, nomes de classes e variáveis DEVEM estar em inglês
//Os textos impressos podem estar em português
//This class implements... (Faça uma descrição breve do que a classe implementa)
public class ControlClass {

    //Attributes
    private int number1;

    //Constructor
    public ControlClass() {

    }

    //Methods
    //This method prints a message saying "Oi Gabriel" (Faça uma descrição breve do que cada método faz)
    public void sayHello() {
        System.out.println("Oi Gabriel!");
    }

    //This method exemplifies how to declare variables in our code
    public void variableDeclarationExample() {
        int i; //Todas as variáveis que for usar no programa devem ser declaradas antes de serem atribuídas ou usadas
        String result;

        i = 10;
        result = "" + i;
        System.out.println(result);
    }
}
