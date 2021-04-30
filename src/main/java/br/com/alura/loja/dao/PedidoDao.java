package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {
	
	private EntityManager em;
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {		
		em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		
		BigDecimal result = this.em.createQuery(jpql, BigDecimal.class).getSingleResult();
		
		return result;
		
	}

// 			FORMA ANTIGA / NAO MUITO CORRETA
//	public List<Object[]> relatorioDeVendas() {
//		
//		String jpql = "SELECT produto.nome, SUM(item.quantidade), MAX(pedido.data) "
//				+ "FROM Pedido pedido "
//				+ "JOIN pedido.itens item "
//				+ "JOIN item.produto produto "
//				+ "GROUP BY produto.nome "
//				+ "ORDER BY item.quantidade DESC";
//
//		return  this.em.createQuery(jpql, Object[].class).getResultList();
//		
//	}
	
	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		
		String jpql = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo(produto.nome, SUM(item.quantidade), MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";

		return  this.em.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
		
	}
	
	public Pedido buscarPedidoComCliente(Long id) {
		
		return this.em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
		
	}

}








