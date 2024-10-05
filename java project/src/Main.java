// Classe base Conta
class Conta {
    protected double saldo;

    public Conta() {
        this.saldo = 0.0;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado.");
        } else {
            System.out.println("Saque inválido. Saldo atual: R$ " + saldo);
        }
    }

    public double verSaldo() {
        return saldo;
    }

    public void transferir(Conta contaDestino, double valor) {
        if (valor > 0 && valor <= saldo) {
            sacar(valor);
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada.");
        } else {
            System.out.println("Transferência inválida. Saldo atual: R$ " + saldo);
        }
    }
}

// Classe ContaCorrente
class ContaCorrente extends Conta {
    public ContaCorrente() {
        super();
    }
}

// Classe ContaEspecial
class ContaEspecial extends Conta {
    private double limite;

    public ContaEspecial(double limite) {
        super();
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && (valor <= saldo + limite)) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado.");
        } else {
            System.out.println("Saque inválido. Saldo atual: R$ " + saldo + ", limite: R$ " + limite);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente();
        ContaEspecial contaEspecial = new ContaEspecial(500); // limite de R$ 500


        System.out.println("=== Conta Corrente ===");
        contaCorrente.depositar(1000);
        System.out.println("Saldo: R$ " + contaCorrente.verSaldo());
        contaCorrente.sacar(500);
        System.out.println("Saldo: R$ " + contaCorrente.verSaldo());


        System.out.println("\n=== Conta Especial ===");
        contaEspecial.depositar(200);
        System.out.println("Saldo: R$ " + contaEspecial.verSaldo());
        contaEspecial.sacar(600); // deve ser permitido, pois o limite é R$ 500
        System.out.println("Saldo: R$ " + contaEspecial.verSaldo());        System.out.println("\n=== Transferência ===");
        contaCorrente.transferir(contaEspecial, 200);
        System.out.println("Saldo Conta Corrente: R$ " + contaCorrente.verSaldo());
        System.out.println("Saldo Conta Especial: R$ " + contaEspecial.verSaldo());
    }
}