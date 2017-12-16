package com.hsf.stdntapt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hsf.stdntapt.entity.Dorm;
import com.hsf.stdntapt.service.DormService;

@Controller
public class DormDailyScoreController {
	@Resource
	DormService dormService;

    @RequestMapping(value="dormDailyScore",method=RequestMethod.GET)
    public String dailyScore(final ModelMap model){
        final List<Dorm> scores = dormService.getDormDailyScore();

        model.addAttribute("scores", scores);
        return "dormDailyScore";
    }
}
