import com.mysql.cj.log.Log;
import com.mysql.cj.log.LogFactory;

import java.sql.SQLException;

public aspect AspSource {
    String Name,Password,URL;
    pointcut publicMethodExecute(): execution(* DataBase.*(..));
    pointcut EntranceToDBase(): call(* DataBase.EntraceToDataBase(..));
    pointcut SetName(String Name):call(* DataBase.SetName(..)) && args(Name);
    pointcut SetPassword(String Password):call(* DataBase.SetPassword(..)) && args(Password);
    pointcut SetURL(String URL):call(* DataBase.SetURL(..)) && args(URL);
    after(): publicMethodExecute(){
        System.out.println("Enter on method " + thisJoinPoint.getSignature());
        Object[] arguments = thisJoinPoint.getArgs();
        for (int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            if (argument != null) {
                System.out.printf("With argument of type %s and value %s. \n", argument.getClass().toString(), argument);
            }
        }
        System.out.println("Exist method");
    }
    void around(String Name):SetName(Name){
        this.Name = Name;
    }
    void around(String Password):SetPassword(Password){
        this.Password = Password;
    }
    void around(String URL):SetURL(URL){
        this.URL = URL;
    }
    after(): EntranceToDBase(){
        //new DataBase().EntraceToDataBase(this.URL, this.Name, this.Password);
        System.out.println("Success entrace to database");
    }
}
