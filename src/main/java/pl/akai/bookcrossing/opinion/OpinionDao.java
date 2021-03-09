package pl.akai.bookcrossing.opinion;

import org.springframework.stereotype.Repository;
import pl.akai.bookcrossing.model.Opinion;

import java.util.List;

@Repository
public interface OpinionDao {

    List<Opinion> getOpinionsByResourceId(int resourceId);
  
    Opinion getOpinionById(int id);

    void insertOpinion(Opinion opinion);

    void updateOpinion(Opinion opinion);
}
