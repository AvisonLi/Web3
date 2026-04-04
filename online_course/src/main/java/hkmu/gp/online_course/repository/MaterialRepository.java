package hkmu.gp.online_course.repository;

import hkmu.gp.online_course.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}