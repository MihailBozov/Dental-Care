package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamDto;
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
    
    public List<TeamDto> getAllTeamMembersForDisplay() {
        List<User> users = this.teamRepository.findAllTeamMembers();
        List<TeamDto> members = new ArrayList<>();
        
        List<User> filteredUsers = users.
                stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .filter(role ->
                                role.getName() == UserRoles.DENTIST ||
                                        role.getName() == UserRoles.DENTAL_ASSISTANT ||
                                        role.getName() == UserRoles.MANAGER)
                        .count() > 0)
                .toList();
        
        
        for (User user : filteredUsers) {
            TeamDto memberDto = this.modelMapper.map(user, TeamDto.class);
            memberDto.setRoles(user.getRoles().stream()
                    .filter(role -> !role.getName().equals(UserRoles.ADMIN) && !role.getName().equals(UserRoles.USER))
                    .map(role -> role.getValue().toString()).toList());
            memberDto.setPictureUrl(user.getImage().getUrl());
            members.add(memberDto);
        }
        
        return members;
    }
}
