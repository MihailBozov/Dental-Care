package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.models.enums.UserRole;
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
        
        List<User> filteredUsers = users.
                stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .filter(role -> role.getName() != UserRole.USER)
                        .count() > 0)
                .toList();
        
        
        for (User user : filteredUsers) {
            TeamDto member = this.modelMapper.map(user, TeamDto.class);
            member.setRoles(user.getRoles());
            
            if (user.getImage() != null) {
                member.setPictureUrl(user.getImage().getUrl());
            }
            members.add(member);
        }
        
        return members;
    }
}
