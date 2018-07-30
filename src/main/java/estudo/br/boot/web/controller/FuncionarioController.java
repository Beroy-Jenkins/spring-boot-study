package estudo.br.boot.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import estudo.br.boot.domain.Cargo;
import estudo.br.boot.domain.Funcionario;
import estudo.br.boot.domain.UF;
import estudo.br.boot.service.CargoService;
import estudo.br.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private CargoService cargoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscatodos());
		return "/funcionario/lista";
	}


	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		if(funcionarioService.validar(funcionario)) {
			funcionarioService.salvar(funcionario);
			attr.addFlashAttribute("success", "Departamento criado com sucesso.");
			return "redirect:/funcionarios/listar";
		}else {
			attr.addFlashAttribute("fail", "Departamento não criado.");
			return "redirect:/funcionarios/cadastrar";
		} 
		
	};
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "/funcionario/cadastro";	
	}
	
	@PostMapping("/editar")
	public String Editar(Funcionario funcionario, RedirectAttributes attr){
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionario editado com sucesso.");
		return "redirect:/funcionarios/listar";	
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		
			funcionarioService.excluir(id);
			attr.addFlashAttribute("success", "Funcionario removido.");
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model){
		model.addAttribute("funcionarios", funcionarioService.buscaPorNome(nome));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model){
		model.addAttribute("funcionarios", funcionarioService.buscaPorCargo(id));
		return "/funcionario/lista";
	}
	
	@GetMapping("/buscar/cargo")
	public String getPorDatas(@RequestParam("entrada") LocalDate entrada,@RequestParam("saida") LocalDate saida, ModelMap model){
		model.addAttribute("funcionarios", funcionarioService.buscaPorDatas(entrada,saida));
		return "/funcionario/lista";
	}
	@ModelAttribute("cargos")
	public List<Cargo> listaCargo(){
		return cargoService.buscatodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUFs(){
		return UF.values();
	}
}
