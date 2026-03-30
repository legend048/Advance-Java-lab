import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

interface FactorialService extends Remote {
    long factorial(int n) throws RemoteException;
}

class FactorialServiceImpl extends UnicastRemoteObject implements FactorialService {
    protected FactorialServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public long factorial(int n) throws RemoteException {
        if (n < 0) {
            throw new RemoteException("Factorial is not defined for negative numbers.");
        }
        if (n > 20) {
            throw new RemoteException("Value too large for long (max supported: 20).");
        }

        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

public class fectorial {
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Usage:");
                System.out.println("  java fectorial server");
                System.out.println("  java fectorial client <number>");
                return;
            }

            if ("server".equalsIgnoreCase(args[0])) {
                LocateRegistry.createRegistry(1099);
                Naming.rebind("rmi://localhost/FactorialService", new FactorialServiceImpl());
                System.out.println("RMI Server started. Service name: FactorialService");
            } else if ("client".equalsIgnoreCase(args[0])) {
                if (args.length < 2) {
                    System.out.println("Provide a number.");
                    return;
                }

                int n = Integer.parseInt(args[1]);
                FactorialService service =
                        (FactorialService) Naming.lookup("rmi://localhost/FactorialService");

                long ans = service.factorial(n);
                System.out.println("Factorial of " + n + " = " + ans);
            } else {
                System.out.println("Invalid mode. Use: server or client");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}