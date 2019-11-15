package com.template.app.rest.service.postagem;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.template.app.entity.PostagemEntity;

@Stateless
public class PostagemService {
	private final static Logger logger = Logger.getLogger(PostagemService.class.getName());
	
	@PersistenceContext(unitName = "blog-persistence-unit")
	private EntityManager entityManater;
	
	public List<PostagemEntity> retrieveAll() {			
		logger.info("PostagemEntity.retrieveAll");				
		List<PostagemEntity> listPostagem = entityManater.createNamedQuery("PostagemEntity.retrieveAll").getResultList();
		return listPostagem;					
	}
	
	public PostagemEntity get(Long id) {
		logger.info("PostagemEntity.get");
		PostagemEntity postagem = GetPostagem(id);
		return postagem;
	}
	
	public PostagemEntity create(PostagemEntity postagemEntity) {	
		logger.info("PostagemEntity.create");
		entityManater.persist(postagemEntity);
		return postagemEntity;				
	}
	
	public void delete(PostagemEntity postagemEntity) {
		entityManater.remove(entityManater.contains(postagemEntity) ? postagemEntity : entityManater.merge(postagemEntity));				
	}

	private PostagemEntity GetPostagem(Long idPostagem) {		
		CriteriaBuilder cb = entityManater.getCriteriaBuilder();
		CriteriaQuery<PostagemEntity> q = cb.createQuery(PostagemEntity.class);
		Root<PostagemEntity> o = q.from(PostagemEntity.class);

		q.where(cb.equal(o.get("id"), idPostagem));

		PostagemEntity comentarioEntity = (PostagemEntity)entityManater.createQuery(q).getSingleResult();
		return comentarioEntity;
	}	
}