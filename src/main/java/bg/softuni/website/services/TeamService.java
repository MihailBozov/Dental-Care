package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamMemberCardDto;
import bg.softuni.website.models.entities.User;
import bg.softuni.website.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    
    private TeamRepository teamRepository;
    private ModelMapper modelMapper;
    
    @Autowired
    public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<TeamMemberCardDto> getTeamMembers() {
        List<User> users = this.teamRepository.findAllByUserRolesSizeGreaterThanOne();
        
        return users
                .stream()
                .map(user -> this.modelMapper.map(user, TeamMemberCardDto.class))
                .collect(Collectors.toList());
        
    }
}
