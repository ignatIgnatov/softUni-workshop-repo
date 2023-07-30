package bg.softuni.cloudinary.web;

import bg.softuni.cloudinary.model.binding.PictureBindingModel;
import bg.softuni.cloudinary.model.view.PictureViewModel;
import bg.softuni.cloudinary.service.PictureService;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class PicturesController {

    private final PictureService pictureService;

    public PicturesController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/add")
    public String addPicture() {
        return "add";
    }

    @PostMapping("/pictures/add")
    public String add(PictureBindingModel pictureBindingModel) throws IOException {

        this.pictureService.addPicture(pictureBindingModel);
        return "redirect:/pictures/all";
    }

    @GetMapping("/pictures/all")
    public String all(Model model) {

        List<PictureViewModel> pics = pictureService.findAll();

        model.addAttribute("pictures", pics);
        return "all";
    }

    @Transactional
    @DeleteMapping("/pictures/delete")
    public String delete(@RequestParam("public_id") String publicId) {
        pictureService.deletePicture(publicId);
        return "redirect:/pictures/all";
    }
}
