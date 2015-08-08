package com.siacra.services;

import com.siacra.daos.CategoriaDao;
import com.siacra.models.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Categoria Service
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 *
 */
@Service("CategoriaService")
@Transactional(readOnly = true)
public class CategoriaService {

    // CategoriaDao is injected...
    @Autowired
    private CategoriaDao categoriaDAO;

    /**
     * Add Categoria
     *
     * @param  categoria Categoria
     */
    @Transactional(readOnly = false)
    public void addCategoria(Categoria categoria) {
        getCategoriaDao().addCategoria(categoria);
    }

    /**
     * Delete Categoria
     *
     * @param   categoria Categoria
     */
    @Transactional(readOnly = false)
    public void deleteCategoria(Categoria categoria) {
        getCategoriaDao().deleteCategoria(categoria);
    }

    /**
     * Update Categoria
     *
     * @param categoria Categoria
     */
    @Transactional(readOnly = false)
    public void updateCategoria(Categoria categoria) {
        getCategoriaDao().updateCategoria(categoria);
    }

    /**
     * Get Categoria
     *
     * @param  id int - Categoria ID
     * @return Categoria By ID - Categoria
     */

    public Categoria getCategoriaById(int id) {
        return getCategoriaDao().getCategoriaById(id);
    }

    /**
     * Get Categoria List
     *
     * @return List Categorias - Categoria
     */

    public List<Categoria> getCategorias() {
        return getCategoriaDao().getCategorias();
    }

    /**
     * Get Categoria DAO
     *
     * @return categoriaDAO - Categoria DAO
     */
    public CategoriaDao getCategoriaDao() {
        return categoriaDAO;
    }

    /**
     * Set Categoria DAO
     *
     * @param  categoriaDAO - Categoria DAO
     */
    public void setCategoriaDao(CategoriaDao categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }
}

