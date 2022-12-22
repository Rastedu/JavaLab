package entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Masters {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_master")
    private int idMaster;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @OneToMany(mappedBy = "idMaster")
    private Collection<Car> carsByIdMaster;

    public int getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(int idMaster) {
        this.idMaster = idMaster;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Masters masters = (Masters) o;

        if (idMaster != masters.idMaster) return false;
        if (firstname != null ? !firstname.equals(masters.firstname) : masters.firstname != null) return false;
        if (lastname != null ? !lastname.equals(masters.lastname) : masters.lastname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMaster;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    public Collection<Car> getCarsByIdMaster() {
        return carsByIdMaster;
    }

    public void setCarsByIdMaster(Collection<Car> carsByIdMaster) {
        this.carsByIdMaster = carsByIdMaster;
    }

    @Override
    public String toString() {
        return "Masters{" +
                "idMaster=" + idMaster +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", carsByIdMaster=" + carsByIdMaster +
                '}';
    }
}
