package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.models.enums.UserRoles;
import bg.softuni.website.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    
    private ModelMapper modelMapper;
    private TeamRepository teamRepository;
    
    @Autowired
    public TeamService(ModelMapper modelMapper, TeamRepository teamRepository) {
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }
    
    public List<TeamDto> getAllTeamMembers() {
        List<User> users = this.teamRepository.findAllTeamMembers();
        List<TeamDto> members = new ArrayList<>();
        
        for (User user : users) {
            TeamDto memberDto = this.modelMapper.map(user, TeamDto.class);
            memberDto.setRole("UNKNOWN");
            for (Role role : user.getRoles()) {
                if (!role.getName().equals(UserRoles.USER) && !role.getName().equals(UserRoles.ADMIN)) {
                    memberDto.setRole(role.getValue());
                    memberDto.setPictureUrl(user.getImage().getUrl());
                }
            }
            members.add(memberDto);
        }
        return members;
    }
}
