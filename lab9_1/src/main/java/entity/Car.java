package entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@NamedQuery(name = "Car.byModel", query = "SELECT e From Car e where e.model= ?1")
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_car")
    private int idCar;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "first_date")
    private Date firstDate;
    @Basic
    @Column(name = "id_master")
    private int idMaster;


    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public int getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(int idMaster) {
        this.idMaster = idMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (idCar != car.idCar) return false;
        if (idMaster != car.idMaster) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (firstDate != null ? !firstDate.equals(car.firstDate) : car.firstDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCar;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (firstDate != null ? firstDate.hashCode() : 0);
        result = 31 * result + idMaster;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar=" + idCar +
                ", model='" + model + '\'' +
                ", firstDate=" + firstDate +
                ", idMaster=" + idMaster +
                '}';
    }
}
