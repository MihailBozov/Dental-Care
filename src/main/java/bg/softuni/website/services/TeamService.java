package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.TeamRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final Logger logger = LoggerFactory.getLogger(TeamService.class);
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    
    @Autowired
    public TeamService(ModelMapper modelMapper, TeamRepository teamRepository) {
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }
    
    @Cacheable("team")
    public List<TeamDto> getAllTeamMembers() {
        logger.warn("getAllTeamMembers() is annotated with @Cacheable and is executed !");
        List<UserEntity> userEntities = this.teamRepository.findAllTeamMembers();
        List<TeamDto> members = new ArrayList<>();
        
        List<UserEntity> filteredUserEntities = userEntities.
                stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .filter(role -> role.getName() != UserRole.USER)
                        .count() > 0)
                .toList();
        
        
        for (UserEntity userEntity : filteredUserEntities) {
            TeamDto member = this.modelMapper.map(userEntity, TeamDto.class);
            member.setRoles(userEntity.getRoles());
            
            if (userEntity.getImage() != null) {
                member.setPictureUrl(userEntity.getImage().getUrl());
            }
            members.add(member);
        }
        
        return members;
    }
}
