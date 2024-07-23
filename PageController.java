package io.datajek.practice.football_players_management;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@Autowired
	PlayerRepo repo;
	
	@RequestMapping("/")
	public String welcome() {
		return "Welcome";
	}
	
	
	
	@RequestMapping("/addplayer")
	public String addPlayer() {
		return "AddPlayer";
	}
	
	@RequestMapping("/viewdetails")
	public String details(Player player) {
		repo.save(player);
		return "ViewPlayerDetails";
	}
	
	@RequestMapping("/searchplayer")
	public String search() {
		return "searchplayer";
	}
	@PostMapping("/searchdetails")
	public ModelAndView viewDetails(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("Retrieve");
		Player player = repo.findById(id).orElse(null);
		mv.addObject(player);
		return mv;
	}
	
	@RequestMapping("/players")
	@ResponseBody
	public List<Player> viewAllPlayers(){
		return repo.findAll();
	}
	
	@RequestMapping("/players/{id}")
	@ResponseBody
	public Optional<Player> viewSelectedPlayers(@PathVariable("id") int id){
		return repo.findById(id);
		
	}
	
	@SuppressWarnings("deprecation")
	@DeleteMapping("/deleteplayer/{id}")
	public Player deletePlayer(@PathVariable("id") int id){
		Player player = repo.getOne(id);
		repo.delete(player);
		return player;
	}
	
	
	@PutMapping(path="/updateplayer", consumes = {"application/json"})
	public Player updateplayer(@RequestBody Player player) {
		repo.save(player);
		return player;
	}	
}
