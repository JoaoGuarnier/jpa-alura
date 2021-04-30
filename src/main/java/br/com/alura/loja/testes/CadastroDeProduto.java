package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {	
		cadastrarProduto();
		Long id = 1l;
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		
		Produto produto = dao.buscarPorId(id);
		
		
//		dao.buscarPorNomeCategoria("CELULARES").stream().forEach(p -> System.out.println(p.getNome()));
		BigDecimal buscarPrecoPorNome = dao.buscarPrecoPorNome("Xiaomi Redmi");
		System.out.println(buscarPrecoPorNome);
		
		
	}
	
	
	

	public static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		
		Produto produto = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(produto);

		em.getTransaction().commit();
		em.close();
	}
	
}
