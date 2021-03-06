package proj.unipe.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import proj.unipe.entities.Mesa;
import proj.unipe.entities.Reserva;

@Repository
public class ReservaDAO extends AbstractDAO<Reserva> {

	private Reserva reserva;
	
	private Session session;
	
	public ReservaDAO() {
		
		super(Reserva.class);
		
		
	}
	// Buscando mesa por numero com CRITERIA(hibernate)
		public List<Reserva> buscaPorNumMesa(int numero) {
			this.session = (Session) manager.getDelegate();

			Criteria crit = session.createCriteria(Mesa.class);
			crit.add(Restrictions.eq("capacidade", numero));
			List<Reserva> results = crit.list();

			return results;
		}
		
		
		public List<Mesa> buscaPorMesaDeReserva(boolean b) {
			this.session = (Session) manager.getDelegate();

			Criteria crit = session.createCriteria(Mesa.class);
			crit.add(Restrictions.eq("deReserva", b));
			List results = crit.list();

			return results;
		}
		
		@SuppressWarnings("unchecked")
		public List<Reserva> buscarReservaPorMesa(Reserva filtro) {
			String sql = "select p from Reserva p where p.mesa.id = :idMesa";
			Query query = manager.createQuery(sql);
			query.setParameter("idMesa", filtro.getMesa().getId());
			return query.getResultList();
		}
		
		
		public List<Reserva> buscarPorNomeResponsavel(String filtro) {
			String sql = "select r from Reserva r where upper(r.nome_responsavel) like upper(:nome_responsavel)";
			Query query = manager.createQuery(sql);
			query.setParameter("nome_responsavel", "%"+filtro+"%");
			return query.getResultList();
		}
	
	public List<Reserva> buscarReserva(Reserva filtro) {
		return filtro.buscarReserva(manager);
	}

}
