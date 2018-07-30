package estudo.br.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import estudo.br.boot.domain.Departamento;
import estudo.br.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	@Autowired
	private DepartamentoService service;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento){
		return "/departamento/cadastro";	
	}
	@GetMapping("/listar")
	public String listar(ModelMap model){
		model.addAttribute("list", service.buscatodos());
		return "/departamento/lista";	
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		if(service.validar(departamento)) {
			service.salvar(departamento);
			attr.addFlashAttribute("success", "Departamento criado com sucesso.");
			return "redirect:/departamentos/listar";
		}else {
			attr.addFlashAttribute("fail", "Departamento não criado.");
			return "redirect:/departamentos/cadastrar";
		} 
		
	};
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("departamento", service.buscarPorId(id));
		return "/departamento/cadastro";	
	}
	
	@PostMapping("/editar")
	public String Editar(Departamento departamento, RedirectAttributes attr){
		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/listar";	
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		if(service.departamentoTemCargos(id)) {
			attr.addFlashAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		}else {
			service.excluir(id);
			attr.addFlashAttribute("success", "Departamento removido.");
		}
		return "redirect:/departamentos/listar";
//		usando model:
//		model.addAttribute("success", "Departamento removido.");
//		ModelMap model 
//		return listar(model);
	}
	
}
