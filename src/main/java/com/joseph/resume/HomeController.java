package com.joseph.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("/")
    public String list(Model model) {
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("set", new Recruiter());
        return "list";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable("id") long id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        addToModel(model, resume);
        model.addAttribute("resume", resume);
        return "view";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Resume resume = resumeRepository.findById(id).get();
        addToModel(model, resume);
        model.addAttribute("resume", resume);
        return "form";
    }

    @GetMapping("/result")
    public String find(Model model, Recruiter recruiter) {
        String finds = "";
        for (Skill skill : skillRepository.findAll()) {
            if (skill.getDescription().toLowerCase().contains(recruiter.getWord().toLowerCase())) {
                finds += (resumeRepository.findById(skill.getParentId()).get().getName()) + "\n";
            }
        }
        recruiter.setSet(finds);
        model.addAttribute("set", recruiter);
        return "find";
    }

    @GetMapping("/add")
    public String form(Model model) {
        model.addAttribute("resume", new Resume());
        return "form";
    }

    @PostMapping("/process")
    public String process(Model model, @Valid Resume resume, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }
        resumeRepository.save(resume);
        model.addAttribute("resumes", resumeRepository.findAll());
        model.addAttribute("set", new Recruiter());
        return "list";
    }

    @GetMapping("/edu")
    public String edu(Model model, Resume resume) {
        resumeRepository.save(resume);
        model.addAttribute("resume", resume);
        Education edu = new Education();
        edu.setParentId(resume.getId());
        model.addAttribute("edu", edu);
        return "education";
    }

    @PostMapping("/saveEdu")
    public String saveEdu(Model model, @Valid Education education, BindingResult result) {
        if (result.hasErrors()) {
            return "education";
        }
        educationRepository.save(education);
        Resume resume = resumeRepository.findById(education.getParentId()).get();
        model.addAttribute("resume", resume);
        addToModel(model, resume);
        return "form";
    }

    @GetMapping("/exp")
    public String exp(Model model, Resume resume) {
        resumeRepository.save(resume);
        model.addAttribute("resume", resume);
        Experience exp = new Experience();
        exp.setParentId(resume.getId());
        model.addAttribute("exp", exp);
        return "experience";
    }

    @PostMapping("/saveExp")
    public String saveExp(Model model, @Valid Experience experience, BindingResult result) {
        if (result.hasErrors()) {
            return "experience";
        }
        experienceRepository.save(experience);
        Resume resume = resumeRepository.findById(experience.getParentId()).get();
        model.addAttribute("resume", resume);
        addToModel(model, resume);
        return "form";
    }

    @GetMapping("/skill")
    public String skill(Model model, Resume resume) {
        resumeRepository.save(resume);
        model.addAttribute("resume", resume);
        Skill skill = new Skill();
        skill.setParentId(resume.getId());
        model.addAttribute("skill", skill);
        return "skill";
    }

    @PostMapping("/saveSkill")
    public String saveSkill(Model model, @Valid Skill skill, BindingResult result) {
        if (result.hasErrors()) {
            return "skill";
        }
        skillRepository.save(skill);
        Resume resume = resumeRepository.findById(skill.getParentId()).get();
        model.addAttribute("resume", resume);
        addToModel(model, resume);
        return "form";
    }

    public void addToModel(Model model, Resume resume) {
        ArrayList<Education> edus = new ArrayList<>();
        for (Education edu : educationRepository.findAll()) {
            if (edu.getParentId() == resume.getId()) {
                edus.add(edu);
            }
        }
        model.addAttribute("edus", edus);
        ArrayList<Experience> exps = new ArrayList<>();
        for (Experience exp : experienceRepository.findAll()) {
            if (exp.getParentId() == resume.getId()) {
                exps.add(exp);
            }
        }
        model.addAttribute("exps", exps);
        ArrayList<Skill> skills = new ArrayList<>();
        for (Skill skill : skillRepository.findAll()) {
            if (skill.getParentId() == resume.getId()) {
                skills.add(skill);
            }
        }
        model.addAttribute("skills", skills);
    }
}
