package estudo.br.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import estudo.br.boot.domain.Cargo;
import estudo.br.boot.domain.Departamento;
import estudo.br.boot.service.CargoService;
import estudo.br.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController{
	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;


	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo){
		return "/cargo/cadastro";	
	}
	@GetMapping("/listar")
	public String listar(ModelMap model){
		model.addAttribute("cargos", cargoService.buscatodos());
		return "/cargo/lista";	
	}

	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr){
		if(cargoService.validarNome(cargo)) {
			cargoService.salvar(cargo);
			attr.addFlashAttribute("success", "cargo criado com sucesso.");
			return "redirect:/cargos/listar";
		}else {
			attr.addFlashAttribute("fail", "cargo não criado.");
			return "redirect:/cargos/cadastrar";
		} 

	};
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "/cargo/cadastro";	
	}

	@PostMapping("/editar")
	public String Editar(Cargo cargo, RedirectAttributes attr){
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "cargo editado com sucesso.");
		return "redirect:/cargos/listar";	
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr){
		if(cargoService.cargoTemFuncionarios(id)){
			attr.addFlashAttribute("fail", "cargo não removido. Funcionarios vinculados");
		}else{
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "cargo removido.");
		}
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamento(){
		return departamentoService.buscatodos();
	}


}
