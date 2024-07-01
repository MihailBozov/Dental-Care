package bg.softuni.website.web;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TeamController {
    
    private TeamService teamService;
    
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    
    @ModelAttribute("allTeamMembers")
    public List<TeamDto> initAllTeamMembers() {
        return this.teamService.getAllTeamMembers();
    }
    
    @GetMapping("/team")
    public String team(Model model) {
        model.addAttribute("currentPage", "teamPage");
        return "teamPage";
    }
}
