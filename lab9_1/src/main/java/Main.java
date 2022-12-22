import entity.Car;
import entity.Masters;
import jakarta.persistence.*;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();

            Query countCarByDept = entityManager.createNativeQuery("Select COUNT(*) FROM Car INNER join masters m on car.id_master = m.id_master Where m.firstname=:mastName");
            countCarByDept.setParameter("mastName","Ivan");
            System.out.println("There are " + countCarByDept.getSingleResult() + " Ivan's repaired/repairing cars");

            //TypedQuery<Car> empByModelQuerry = entityManager.createNamedQuery("Car.byModel", Car.class);
            //empByModelQuerry.setParameter(1,"VAZ 2107");
            //for (Car car : empByModelQuerry.getResultList()) {
               // System.out.println(car);}
            //Car car = new Car();
            //car.setIdMaster(1);
            //car.setFirstDate(Date.valueOf("2022-10-16"));
            //car.setModel("Lada");
            //car.setIdCar(5);
            //entityManager.merge(car);


            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
