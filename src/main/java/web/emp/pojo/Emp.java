package web.emp.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@Entity
public class Emp {
    @Id
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Timestamp hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

//    ======單向======
//    @ManyToOne
//    @JoinColumn(name = "DEPTNO", insertable = false, updatable = false)


//    ======雙向======
    @ManyToOne
    @JoinColumn(name = "DEPTNO", insertable = false, updatable = false)
    private Dept dept;
}

