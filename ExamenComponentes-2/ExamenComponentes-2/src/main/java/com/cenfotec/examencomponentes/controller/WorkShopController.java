package com.cenfotec.examencomponentes.controller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cenfotec.examencomponentes.domain.Categoria;
import com.cenfotec.examencomponentes.domain.Tarea;
import com.cenfotec.examencomponentes.domain.Workshop;
import com.cenfotec.examencomponentes.service.CategoriaService;
import com.cenfotec.examencomponentes.service.ClaveService;
import com.cenfotec.examencomponentes.service.TareaService;
import com.cenfotec.examencomponentes.service.WorkshopService;

@Controller
public class WorkShopController {
	@Autowired 
	WorkshopService workshopService;
	
	@Autowired
	ClaveService claveService;
	
	@Autowired
	TareaService tareaService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}

    @RequestMapping(value = "/registroWorkshop", method = RequestMethod.GET)
    public String insertarPage(Model model) {
        model.addAttribute(new Workshop());
        model.addAttribute("categoria", categoriaService.getAll());
        return "registroWorkshop";
    }
    
    @RequestMapping(value = "/registroWorkshop", method = RequestMethod.POST)
    public String insertarAction(Workshop workshop, BindingResult result, Model model) {
    	
        workshopService.save(workshop);
        return "index";
    }
	
    @RequestMapping(value = "/registroCategoria", method = RequestMethod.GET)
    public String insertarCategorias(Model model) {
        model.addAttribute(new Categoria());
        return "registroCategoria";
    }
    
    
    @RequestMapping(value = "/registroCategoria", method = RequestMethod.POST)
    public String insertarCategoria(Categoria category, BindingResult result, Model model) {
        categoriaService.save(category);
        return "index";
    }
    
    @RequestMapping("/listarCategorias")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.getAll());
        return "listarCategorias";
    }
    
    @RequestMapping("/listarWorkshop")
    public String listarWorkshop(Model model) {
        model.addAttribute("workshop", workshopService.getAll());
        return "listarWorkshop";
    }
    
	@RequestMapping("/editarCategoria/{id}")
	public String findCategoriaToEdit(Model model, @PathVariable long id) {
		Optional<Categoria> possibleData = categoriaService.get(id);
		if (possibleData.isPresent()) {
			model.addAttribute("categoriaToEdit",possibleData.get());
			return "editarCategoria";	
		}
		return "notfound";
	}

	@RequestMapping(value="/editarCategoria/{id}",  method = RequestMethod.POST)
	public String saveEdition(Categoria categoria, Model model, @PathVariable long id) {
		categoriaService.save(categoria);
		return "index";
	}
	
	
    @RequestMapping(value = "/deleteCategoria/{id}", method = RequestMethod.POST)
    public String deleteEdition(Categoria cat, Model model, @PathVariable long id) {
        categoriaService.delete(cat);
        return "borrarCategoriaConfirmacion";
    }
    
    @RequestMapping("/deleteCategoria/{id}")
    public String findCatToDelete(Model model, @PathVariable long id) {
        Optional<Categoria> possibleData = categoriaService.get(id);
        if (possibleData.isPresent()) {
            model.addAttribute("categoria", possibleData.get());
            return "borrarCategoria";
        }
        return "notfound";
    }
	
	
    @RequestMapping(value = "/detalleWork/{id}")
    public String saveEdition(Model model, @PathVariable long id) {
        Optional<Workshop> possibleData = workshopService.get(id);
        Workshop newWork = new Workshop();
        if (possibleData.isPresent()) {
            int tiempo=0;
            for (Tarea act: possibleData.get().getTareas()) {
               tiempo+=act.getTiempo();
            }
            newWork.setTareas(possibleData.get().getTareas());
            newWork.setId(possibleData.get().getId());
        	newWork.setTiempoTotal(tiempo);
        	newWork.setName(possibleData.get().getName());
        	newWork.setAutor(possibleData.get().getAutor());
        	newWork.setObjetivo(possibleData.get().getObjetivo());
        	newWork.setKeyWord(possibleData.get().getKeyWord());
            model.addAttribute("newWork", newWork);
            return "detalleWork";
        }
        return "notfound";
    }
    
    @RequestMapping(value = "/registrarActividad/{id}")
    public String recoverForAddActivity(Model model, @PathVariable long id) {
        Optional<Workshop> workshop = workshopService.get(id);
        Tarea newActivity = new Tarea();
        if (workshop.isPresent()) {
            newActivity.setWorkshop(workshop.get());
            model.addAttribute("workshop", workshop.get());
            model.addAttribute("tarea", newActivity);
            return "registrarActividad";
        }
        return "notfound";
    }

    @RequestMapping(value = "/registrarActividad/{id}", method = RequestMethod.POST)
    public String saveTarea(Tarea tarea, Model model, @PathVariable long id) {
        Optional<Workshop> workshop = workshopService.get(id);
        if (workshop.isPresent()) {
        	tarea.setWorkshop(workshop.get());
            tareaService.save(tarea);
            return "index";
        }
        return "errorArticle";
    }
    
    @RequestMapping(value = "/buscarWork", method = RequestMethod.GET)
    public String buscarPage(Model model) {
        model.addAttribute(new Workshop());
        model.addAttribute("categoria", categoriaService.getAll());
        return "buscarWork";
    }

    @RequestMapping("/listarKey")
    public String listarKey(Model model, @RequestParam String keyWord) {
        model.addAttribute("workshopKey", workshopService.findKey(keyWord));
        return "listadoXKey";
    }
    
    @RequestMapping("/listarNombre")
    public String listarNombre(Model model, @RequestParam String name) {
        model.addAttribute("workshopNombre", workshopService.find(name));
        return "listadoXNombre";
    }
    
    @RequestMapping("/listarCategoria")
    public String listarCategoria(Model model, @RequestParam String categoria) {
        model.addAttribute("workshopCategoria", workshopService.findCategoria(categoria));
        return "listarXCategoria";
    }
    
    
    @RequestMapping("/listarAutor")
    public String listarAutor(Model model, @RequestParam String autor) {
        model.addAttribute("workshopAutor", workshopService.findAutor(autor));
        return "listarXAutor";
    }
    
    @RequestMapping("/editarWork/{id}")
    public String findWorkshopToEdit(Model model, @PathVariable long id) {
        Optional<Workshop> possibleData = workshopService.get(id);
        if (possibleData.isPresent()) {
            model.addAttribute("categoria", categoriaService.getAll());
            model.addAttribute("workshopToEdit", possibleData.get());
            return "editarWork";
        }
        return "notfound";
    }
    

    @RequestMapping(value = "/editarWork/{id}", method = RequestMethod.POST)
    public String saveEdition(Workshop workshop, Model model, @PathVariable long id) {
        workshopService.save(workshop);
        return "index";
    }
    
    
    @RequestMapping(value = "/word/{id}")
    public String getGeneratedDocument(Model model, @PathVariable long id) throws IOException {
        Optional<Workshop> possibleData = workshopService.get(id);
        if (possibleData.isPresent()) {
            int tiempo=0;
            String actividades="";
            for (Tarea act: possibleData.get().getTareas()) {
               tiempo+=act.getTiempo();
            }
            XWPFDocument document = new XWPFDocument();
            String output = possibleData.get().getName() + ".docx";
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText(possibleData.get().getName());
            titleRun.setColor("000000");
            titleRun.setBold(true);
            titleRun.setFontFamily("Arial");
            titleRun.setFontSize(20);

            XWPFParagraph title2=document.createParagraph();
            title2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun2 = title2.createRun();
            titleRun2.setText("Duracion: "+tiempo+" minutos");
            titleRun2.setColor("000000");
            titleRun2.setBold(true);
            titleRun2.setFontFamily("Arial");
            titleRun2.setFontSize(20);

            XWPFParagraph subTitle = document.createParagraph();
            subTitle.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun subTitleRun = subTitle.createRun();
            subTitleRun.setText("Impartido por :"+possibleData.get().getAutor());
            titleRun2.setColor("000000");
            titleRun2.setBold(true);
            titleRun2.setFontFamily("Arial");
            titleRun2.setFontSize(20);

            XWPFParagraph sectionTitle = document.createParagraph();
            XWPFRun sectionTRun = sectionTitle.createRun();
            sectionTRun.setText("Categoria "+possibleData.get().getCategoria());
            sectionTRun.setColor("000000");
            sectionTRun.setBold(true);
            sectionTRun.setFontFamily("Arial");

            for (Tarea act: possibleData.get().getTareas()) {
                XWPFParagraph sub = document.createParagraph();
                sub.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun subTitleRu = sub.createRun();
                subTitleRu.setColor("000000");
                subTitleRu.setFontFamily("Arial");
                subTitleRu.setFontSize(16);
                subTitleRu.setTextPosition(20);
                subTitleRu.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
                actividades= act.getName()+"\n"+
                        "Descripcion "+act.getDescripcion()+"\n"+
                        "Nota "+act.getTexto() +"\n";
                subTitleRun.setText(actividades);

            }

            FileOutputStream out = new FileOutputStream(output);
            document.write(out);
            out.close();
            document.close();
            return "index";
        } else {
            return "notfound";
        }
    }
	
}
