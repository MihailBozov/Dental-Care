package bg.softuni.website.services;

import bg.softuni.website.models.dtos.TeamDto;
import bg.softuni.website.models.entities.Image;
import bg.softuni.website.models.entities.Role;
import bg.softuni.website.models.entities.Testimonial;
import bg.softuni.website.models.entities.UserEntity;
import bg.softuni.website.models.enums.UserRole;
import bg.softuni.website.repositories.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private TeamRepository teamRepository;
    
    @InjectMocks
    private TeamService teamService;
    
    private TeamDto teamDto1;
    private TeamDto teamDto2;
    
    @BeforeEach
    void setUp() {
        teamDto1 = new TeamDto();
        teamDto1.setFirstName("Sand");
        teamDto1.setRoles(List.of(new Role(UserRole.USER)));
        teamDto1.setPictureUrl("pictureUrl1");
        
        teamDto2 = new TeamDto();
        teamDto2.setFirstName("Yanica");
        teamDto2.setRoles(List.of(new Role(UserRole.USER)));
        teamDto2.setPictureUrl("pictureUrl2");
    }
    
    @Test
    void getAllTeamMembers() {
        UserEntity userEntity1 = new UserEntity();
        Role role1 = new Role();
        role1.setName(UserRole.MANAGER);
        userEntity1.setRoles(List.of(role1));
        userEntity1.setImage(new Image());
        userEntity1.getImage().setUrl("pictureUrl1");
        
        UserEntity userEntity2 = new UserEntity();
        Role role2 = new Role();
        role2.setName(UserRole.MANAGER);
        userEntity2.setRoles(List.of(role2));
        userEntity2.setImage(new Image());
        userEntity2.getImage().setUrl("pictureUrl2");
        
        when(teamRepository.findAllTeamMembers())
                .thenReturn(List.of(userEntity1, userEntity2));
        
        when(modelMapper.map(userEntity1, TeamDto.class))
                .thenReturn(teamDto1);
        
        when(modelMapper.map(userEntity2, TeamDto.class))
                .thenReturn(teamDto2);
        
        List<TeamDto> result = teamService.getAllTeamMembers();
        
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        
        Assertions.assertEquals("Sand", result.get(0).getFirstName());
        Assertions.assertEquals("Yanica", result.get(1).getFirstName());
        
        Assertions.assertEquals("pictureUrl1", result.get(0).getPictureUrl());
        Assertions.assertEquals("pictureUrl2", result.get(1).getPictureUrl());
        
        Assertions.assertTrue(result.get(0).getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.MANAGER)));
        Assertions.assertTrue(result.get(1).getRoles().stream().anyMatch(role -> role.getName().equals(UserRole.MANAGER)));
    }
}






