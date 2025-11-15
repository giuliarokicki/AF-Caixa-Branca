package login;

public class Main {

    public static void main(String[] args) {

        User user = new User();

        boolean autenticado = user.verificarUsuario("admin", "123");

        if (autenticado) {
            System.out.println("Usuário autenticado com sucesso!");
            System.out.println("Nome retornado: " + user.nome);
        } else {
            System.out.println("Falha na autenticação.");
        }
    }
}
