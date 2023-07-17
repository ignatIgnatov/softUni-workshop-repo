package com.example.judgeV2.web;

import com.example.judgeV2.model.binding.CommentAddBindingModel;
import com.example.judgeV2.model.service.CommentServiceModel;
import com.example.judgeV2.model.view.HomeworkViewModel;
import com.example.judgeV2.repository.CommentRepository;
import com.example.judgeV2.service.CommentService;
import com.example.judgeV2.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/comments")
public class CommentsController {

    private final HomeworkService homeworkService;
    private final ModelMapper modelMapper;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentsController(HomeworkService homeworkService, ModelMapper modelMapper, CommentService commentService, CommentRepository commentRepository) {
        this.homeworkService = homeworkService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/add")
    public String add(Model model) {

        if(!model.containsAttribute("commentAddBindingModel")) {
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        HomeworkViewModel homework = modelMapper.map(homeworkService.findHomeworkForScoring(), HomeworkViewModel.class);
        model.addAttribute("homework", homework);
        return "homework-check";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CommentAddBindingModel commentAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "redirect:add";
        }

        CommentServiceModel serviceModel = modelMapper
                .map(commentAddBindingModel, CommentServiceModel.class);

        commentService.add(serviceModel, commentAddBindingModel.getHomeworkId());

        return "redirect:/";
    }
}
