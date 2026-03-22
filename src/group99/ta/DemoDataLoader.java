package group99.ta;

import group99.ta.model.Opportunity;
import group99.ta.service.OpportunityService;

/**
 * Loads initial demo data so that the prototype can be demonstrated quickly.
 */
public class DemoDataLoader {
    private final OpportunityService opportunityService;

    public DemoDataLoader(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    public void seedIfEmpty() {
        if (!opportunityService.getAllOpportunities().isEmpty()) {
            return;
        }

        Opportunity o1 = new Opportunity(
                "OPP001",
                "CS201",
                "Data Structures & Algorithms",
                "TA Role",
                "8-10 hours/week",
                "2026-03-30",
                "IELTS 6.5+",
                "C++, Algorithm Design, Problem Solving, Teaching",
                true,
                true,
                "Programming Screening Task",
                "2026-03-28",
                "Assist teaching, support labs, answer questions and grade basic exercises."
        );

        Opportunity o2 = new Opportunity(
                "OPP002",
                "CS302",
                "Introduction to Database Systems",
                "TA Role",
                "6-8 hours/week",
                "2026-03-31",
                "IELTS 6.0+",
                "SQL, Database Design, Communication",
                false,
                true,
                "SQL Design Mini Task",
                "2026-03-29",
                "Support practical sessions, answer SQL questions and assist project groups."
        );

        opportunityService.postOpportunity(o1);
        opportunityService.postOpportunity(o2);
    }
}