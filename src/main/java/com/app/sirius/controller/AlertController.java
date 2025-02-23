package com.app.sirius.controller;

import com.app.sirius.domain.Alert;
import com.app.sirius.service.AlertService;
import com.sun.net.httpserver.HttpContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alert")
@Tag(name = "Alert API", description = "API for managing alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    public AlertController(){System.out.println("###LOG : AlertController() 생성");}

    @GetMapping("/detail")
    @ResponseBody
    @Operation(summary = "Get Alert Detail", description = "Retrieves detailed information for a specific alert by alertId and connection context.")
    public ResponseEntity<Map<String, Object>> detail(@RequestParam Long alertId){
        List<Alert> list = alertService.detail(alertId);
        Map<String, Object> response = new HashMap<>();
        if (list != null && !list.isEmpty()){
            for (Alert alert : list) {
                Map<String, Object> alertDetails = new HashMap<>();
                alertDetails.put("status", "confirmed");
                alertDetails.put("alert_id", alert.getAlertId());
                alertDetails.put("deviceId", alert.getDeviceId());
                alertDetails.put("deviceLocation", alert.getDeviceLocation());
                alertDetails.put("alertType", alert.getAlertType());
                if (alert.getAlertedTime() != null) { alertDetails.put("alert_id", alert.getAlertId()); }
                response.put("", alertDetails);
            }
        } else {
            response.put("status","denied. Alert not found");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    @Operation(summary = "List Alerts", description = "Displays a paginated list of alerts.")
    public ResponseEntity<Map<String, Object>> list(){

        List<Alert> list = alertService.list();
        Map<String, Object> response = new HashMap<>();
        if (list != null && !list.isEmpty()){
            for (Alert alert : list) {
                Map<String, Object> alertDetails = new HashMap<>();
                alertDetails.put("status", "confirmed");
                alertDetails.put("alert_id", alert.getAlertId());
                alertDetails.put("deviceId", alert.getDeviceId());
                alertDetails.put("deviceLocation", alert.getDeviceLocation());
                alertDetails.put("alertType", alert.getAlertType());
                if (alert.getAlertedTime() != null) { alertDetails.put("alert_id", alert.getAlertId()); }
                response.put("", alertDetails);
            }
        } else {
            response.put("status","denied. Alert not found");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @Operation(summary = "Delete Alert", description = "Deletes an alert by its ID and returns a deletion result page.")
    public ResponseEntity<Map<String, Object>> deleteSuccess(@RequestParam long alertId){
        Map<String,Object > response = new HashMap<>();
        if (alertService.delete(alertId)==1){
            response.put("status","confirmed");
        } else { response.put("status","denied"); }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/pageRows")
    @Operation(summary = "Set Page Rows", description = "Sets the number of rows per page in the session and redirects to the alert list.")
    public String pageRows(Integer page, Integer pageRows){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        session.setAttribute("pageRows", pageRows);
        return "redirect:/alert/list?page="+page;
    }


}
