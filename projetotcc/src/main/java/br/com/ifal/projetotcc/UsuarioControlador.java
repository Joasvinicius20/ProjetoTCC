package br.com.ifal.projetotcc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UsuarioControlador{

    @Autowired
    UsuarioRepositorio repo;

    @RequestMapping("/usuario/form")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("formaluno.html");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping("/usuario/salvar")
    public ModelAndView salvar(Usuario novoUsuario) {
        repo.save(novoUsuario);
        return new ModelAndView("redirect:/usuario/listar");
    }

    @RequestMapping("/usuario/listar")
    public ModelAndView listar() {
        ModelAndView resposta = new ModelAndView("listausuario.html");
        resposta.addObject("usuarios", repo.findAll());
        return resposta;
    }

    @RequestMapping("/usuario/excluir/{id}")
    public ModelAndView excluir(@PathVariable(name = "id") int id) {
        repo.deleteById(id);
        return new ModelAndView("redirect:/usuario/listar");
    }

    @RequestMapping("/usuario/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable(name = "id") int id) {
        ModelAndView resposta = new ModelAndView("formusuario.html");
        resposta.addObject("usuario", repo.findById(id).get());
        return resposta;
    }

}