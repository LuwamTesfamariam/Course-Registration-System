package registration.registrationsystem.Util;

import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import registration.registrationsystem.domain.*;
import registration.registrationsystem.service.impl.RegistrationEventService;
import registration.registrationsystem.service.impl.RegistrationRequestService;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationSystemUtility {

    public static String getEventStatus(RegistrationEvent registrationEvent){
        LocalDateTime now = LocalDateTime.now();
        String  status = "";
        if (now.isBefore(registrationEvent.getEndDateTime()) && now.isAfter(registrationEvent.getStartDateTime())){
            status = "Open";
        }
        else{
            status = "closed";
        }
        return status;
    }

    public static Registration convertToRegistration(List<RegistrationRequest> requests, List<CourseOffering> coursesOfferedInABlock){
        Comparator<RegistrationRequest> registrationRequestComparator = new Comparator<>() {
            @Override
            public int compare(RegistrationRequest o1, RegistrationRequest o2) {
                return o1.getPriorityNumber() - o2.getPriorityNumber();
            }
        };
        List<RegistrationRequest> sortedRequests = requests.stream().sorted(registrationRequestComparator).collect(Collectors.toList());
        for(RegistrationRequest request: sortedRequests){
            CourseOffering courseRequested = request.getCourseOffering();
            if(coursesOfferedInABlock.contains(courseRequested) && courseRequested.getAvailableSeats() > 0 && !request.isProcessed()){
                request.setProcessed(true);
                return new Registration(request.getStudent(), request.getCourseOffering());
            }
        }
        return null;
    }


}
