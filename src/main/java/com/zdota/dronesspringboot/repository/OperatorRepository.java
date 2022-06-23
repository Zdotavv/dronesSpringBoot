package com.zdota.dronesspringboot.repository;


import com.zdota.dronesspringboot.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OperatorRepository extends JpaRepository<Operator, Integer> {

    @Query("SELECT o from Operator o inner join Drone dr on o.id = dr.mainOperator.id")
     Operator getOperatorByIdOfDrone(Integer id);
}
