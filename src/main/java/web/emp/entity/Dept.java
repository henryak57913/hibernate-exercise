package web.emp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Dept {
    @Id
    private Integer deptno;
    private String dname;
    private String loc;
//    ======單向======
//    @OneToMany
//    @JoinColumn(name = "DEPTNO"
//            , referencedColumnName = "DEPTNO")

//    ======雙向======
@OneToMany(mappedBy = "dept")
    private List<Emp> emps;

}


