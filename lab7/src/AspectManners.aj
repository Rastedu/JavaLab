import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public aspect AspectManners {
    String name = null;
    pointcut anyMethod(): execution(* HelloWorld.*(..));
    pointcut sayMethod():execution(* HelloWorld.say*(..));
    pointcut methodsWithoutSay():call(* HelloWorld.*(..)) && !sayMethod();
    pointcut LogSet():execution(* HelloWorld.get*(..));
    pointcut LogGet():execution(* HelloWorld.set*(..));
    pointcut GetName():call(* HelloWorld.getFamilyName(..));
    pointcut SetName(String FamName):call(* HelloWorld.setFamilyName(..)) && args(FamName);
    void around(String FamName):SetName(FamName){
        name = FamName;
    }
    pointcut GetName1():call(* HelloWorld.getName(..));
    //after() returning(String object1):GetName1(){name = name + object1;}
    before():sayMethod(){
        System.out.println("Good day!");
    }
    after():sayMethod(){
        System.out.println("Nice to meet you!");
    }
    after():LogSet(){
        System.out.println("Something was getted."  + thisJoinPoint +  "Timestamp:" + System.currentTimeMillis());
    }
    after():LogGet(){
        System.out.println("Something was setted."  + thisJoinPoint +  "Timestamp:" + System.currentTimeMillis());
    }

    after():methodsWithoutSay(){
        System.out.println("Without say method is called");
    }
    pointcut saySecName(String person): call(* HelloWorld.sayToPerson(String,String)) && args(*,person); //&& call(* HelloWorld.setFamilyName(..)) && args(FamilyName);
    void around(String person): saySecName(person){
        proceed(person + " " + name );
    }
}
