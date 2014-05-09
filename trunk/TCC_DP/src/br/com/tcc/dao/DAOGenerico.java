package br.com.tcc.dao;

import br.com.tcc.exceptions.CannotDeleteException;
import br.com.tcc.modelo.IModelo;
import br.com.tcc.util.HibernateUtil;
import br.com.tcc.util.Util;
import com.zap.arca.event.DataController;
import com.zap.arca.event.DataListener;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.*;
import org.hibernate.exception.ConstraintViolationException;

/**
 * DOCUMENTAÇÃO DA CLASSE
 * <br><br>
 * FINALIDADE: métodos básicos para a interação com o banco (GENÉRICO)
 * <br>
 *
 * <br><br>
 * HISTÓRICO DE DESENVOLVIMENTO:
 * <br>
 * 21/04/2013 - @author Edidelson - Primeira versão
 *
 * <br><br>
 * LISTA DE CLASSES INTERNAS:
 * <br>
 *
 * <br><br>
 * ERROS CONHECIDOS:
 * <br>
 *
 * <br><br>
 */

public abstract class DAOGenerico implements IDAOGenerico {

    protected static final Logger logger = Logger.getLogger("br.com.tcc.dao");
    protected static Session session;
    protected static DataController controler;

    static {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        controler = DataController.getInstance();
    }

    public synchronized void addDataListener(DataListener l) {
        controler.addDataListener(l);
    }

    public synchronized void removeDataListener(DataListener l) {
        controler.removeDataListener(l);
    }

    /**
     * Inicializa uma collection
     * @param obj
     */
    public static void initialize(Object obj) {
        Hibernate.initialize(obj);

    }

    /**
     * Adiciona um objeto que implemente IModelo no banco de dados
     * @param m - IModelo - objeto que implemente IModelo
     */
    public void adicionar(IModelo m) {
        try {
            abrirSessao();
            getSession().beginTransaction();
            getSession().save(m);
            getSession().getTransaction().commit();

            logger.log(Level.INFO, "{0}, com o código {1} adicionado",
                    new Object[] {m.getClass().getName(), m.getCodigo()});
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw ex;
        } 
//        finally {
//            fecharSessao();
//        }
    }

    /**
     * Altera um objeto armazenado no banco de dados
     * @param m - IModelo - objeto que implemente IModelo
     */
    public void alterar(IModelo m) {
        try {
            abrirSessao();
            getSession().clear();
            getSession().beginTransaction();
            getSession().merge(m);
            getSession().getTransaction().commit();

            logger.log(Level.INFO, "{0}, com o código {1} alterado",
                    new Object[] {m.getClass().getName(), m.getCodigo()});
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw ex;
        }
        finally {
            fecharSessao();
        }
    }

    /**
     * Consulta um objeto armazenado no banco de dados
     * @param classe - Class - Classe do objeto desejado
     * @param identificador - Serializable - identificador do objeto
     * @return IModelo - resultado da consulta, null caso não encontre o objeto
     * @deprecated Substituído por consultar(Class, Serializable, boolean)
     */
    @Deprecated
    protected Object consultar(Class classe, Serializable identificador) {
        Object obj = null;
        try {
            abrirSessao();
            getSession().beginTransaction();
            obj = getSession().get(classe, identificador);
            getSession().getTransaction().commit();

            // Verificando se o objeto resultado está inativo
            // caso esteja, então o método retornará null
            if(obj instanceof IModelo) {
                obj = ((IModelo) obj).isInativo()? null: obj;
            }
            return obj;
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            Util.logException(ex);
            throw ex;
        }
    }

    /**
     * Consulta um objeto armazenado no banco de dados
     * @param classe - Class - Classe do objeto desejado
     * @param identificador - Serializable - identificador do objeto
     * @param comInativo - Se TRUE retornará objetos inativos, se FALSE retornará NULL para esses objetos
     * @return - Object - Objeto correspondente a classe e identificador informados ou NULL caso não seja encontrado
     */
    protected Object consultar(Class classe, Serializable identificador, boolean comInativo) {
        Object obj = null;
        try {
            abrirSessao();
            getSession().beginTransaction();
            obj = session.get(classe, identificador);
            getSession().getTransaction().commit();

            // Verifica se deve retornar objetos inativos
            if(!comInativo) {
                if(obj instanceof IModelo) {
                    obj = ((IModelo) obj).isInativo()? null: obj;
                }
            }
            return obj;
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw ex;
        }
    }

    /**
     * Retorna uma lista de acordo com a namedQuery informada
     * @param <T>
     * @param namedQuery - String - nome da namedQuery de consulta
     * @return Lista de algum objeto que implemente IModelo
     */
    public <T extends IModelo> List<T> getLista(String namedQuery) {
        List<T> modelos = null;
        try {
            abrirSessao();
            getSession().beginTransaction();
            modelos = session.getNamedQuery(namedQuery).list();
            getSession().getTransaction().commit();

            logger.log(Level.INFO, "{0} retornou {1} registros.",
                    new Object[] {namedQuery, modelos.size()});
            return modelos;
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw ex;
        }
    }

    /**
     * Remove um objeto do banco de dados
     * @param m - algum objeto que implemente IModelo
     */
    public void remover(IModelo m) throws CannotDeleteException {
        try {
            abrirSessao();
            getSession().beginTransaction();
            getSession().delete(m);
            getSession().getTransaction().commit();

            logger.log(Level.WARNING, "{0}, de código {1} removido.",
                    new Object[] {m.getClass().getName(), m.getCodigo()});
        } catch(ConstraintViolationException ex) {
            getSession().getTransaction().rollback();
            throw new CannotDeleteException();
        } catch(StaleStateException ex) {
            getSession().getTransaction().rollback();
            throw new CannotDeleteException("O registro já foi removido por outro cliente");
        } catch(HibernateException ex) {
            getSession().getTransaction().rollback();
            ex.printStackTrace();
            Util.logException(ex);
            throw ex;
        } finally {
            fecharSessao();
        }
    }

    /**
     * Retorna a Session atualmente em uso na aplicação
     * @return Session em uso
     */
    public static Session getSession() {
        return session;
    }

    /**
     * Verifica se a sessão está aberta, caso não estiver a abre.
     */
    public static void abrirSessao() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        if (!session.isOpen()) {
            session = sessionFactory.openSession();

            logger.log(Level.INFO, "Sessão aberta");
        }
    }

    /**
     * Fecha a sessão caso esteja aberta e dispara um aviso de atualização
     * para todos os ouvintes registrados
     */
    public static void fecharSessao() {
        if (session.isOpen()) {
            session.close();

            logger.log(Level.INFO, "Sessão fechada");
        }
        controler.fireDataUpdate();
    }

    /**
     * Adiciona os parametros de um Map na query informada e a retorna pronta
     * para execução.
     * @param query
     * @param parametros
     * @return
     */
    public static Query getQuery(String query, Map parametros) {
        String[] nomes = new String[parametros.size()];
        parametros.keySet().toArray(nomes);

        Query q = DAOGenerico.getSession().createQuery(query);
        for(String n: nomes) {
            q.setParameter(n, parametros.get(n));
        }

        return q;
    }
}
