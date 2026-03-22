

import group99.ta.model.ApplicantProfile;
import group99.ta.model.ApplicationRecord;
import group99.ta.model.Opportunity;
import group99.ta.service.ApplicationService;
import group99.ta.service.OpportunityService;
import group99.ta.service.ProfileService;
import group99.ta.storage.DataStore;

import java.util.List;
import java.util.Scanner;

/**
 * Simple stand-alone Java console application.
 * This is enough for first assessment GitHub evidence:
 * - can create applicant profile
 * - can browse opportunities
 * - can post opportunities
 * - can submit application
 * - can upload screening task
 * - can review applications
 */
public class App {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        ProfileService profileService = new ProfileService(dataStore);
        OpportunityService opportunityService = new OpportunityService(dataStore);
        ApplicationService applicationService = new ApplicationService(dataStore, profileService, opportunityService);
        DemoDataLoader demoDataLoader = new DemoDataLoader(opportunityService);

        demoDataLoader.seedIfEmpty();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BUPT TA Recruitment System (Sprint 1 Prototype) =====");
            System.out.println("1. Create / Update Applicant Profile");
            System.out.println("2. Browse Opportunities");
            System.out.println("3. View Opportunity Details");
            System.out.println("4. Submit Application");
            System.out.println("5. Upload Screening Task");
            System.out.println("6. Module Organiser: Post Opportunity");
            System.out.println("7. Module Organiser: Review Applications");
            System.out.println("8. View My Applications");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        handleSaveProfile(scanner, profileService);
                        break;
                    case "2":
                        handleBrowseOpportunities(scanner, opportunityService);
                        break;
                    case "3":
                        handleViewOpportunityDetails(scanner, opportunityService);
                        break;
                    case "4":
                        handleSubmitApplication(scanner, applicationService);
                        break;
                    case "5":
                        handleUploadScreening(scanner, applicationService);
                        break;
                    case "6":
                        handlePostOpportunity(scanner, opportunityService);
                        break;
                    case "7":
                        handleReviewApplications(scanner, applicationService);
                        break;
                    case "8":
                        handleViewMyApplications(scanner, applicationService);
                        break;
                    case "0":
                        System.out.println("Bye.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private static void handleSaveProfile(Scanner scanner, ProfileService profileService) {
        ApplicantProfile profile = new ApplicantProfile();

        System.out.print("Applicant ID: ");
        profile.setApplicantId(scanner.nextLine());

        System.out.print("Full name: ");
        profile.setFullName(scanner.nextLine());

        System.out.print("Student ID: ");
        profile.setStudentId(scanner.nextLine());

        System.out.print("Email: ");
        profile.setEmail(scanner.nextLine());

        System.out.print("Programme: ");
        profile.setProgramme(scanner.nextLine());

        System.out.print("Year of study: ");
        profile.setYearOfStudy(scanner.nextLine());

        System.out.print("English qualification type (IELTS/TOEFL/etc.): ");
        profile.setEnglishType(scanner.nextLine());

        System.out.print("English score: ");
        profile.setEnglishScore(scanner.nextLine());

        System.out.print("English test date: ");
        profile.setEnglishDate(scanner.nextLine());

        System.out.print("CV file name: ");
        profile.setCvFileName(scanner.nextLine());

        System.out.print("Availability (e.g. Mon Morning; Tue Afternoon): ");
        profile.setAvailability(scanner.nextLine());

        System.out.print("Skills: ");
        profile.setSkills(scanner.nextLine());

        profileService.saveProfile(profile);
        System.out.println("Profile saved. Completion = " + profile.getCompletionPercent() + "%");
    }

    private static void handleBrowseOpportunities(Scanner scanner, OpportunityService opportunityService) {
        System.out.print("Enter keyword for search (or leave blank to list all): ");
        String keyword = scanner.nextLine();

        List<Opportunity> opportunities = keyword.trim().isEmpty()
                ? opportunityService.getAllOpportunities()
                : opportunityService.searchByKeyword(keyword);

        if (opportunities.isEmpty()) {
            System.out.println("No opportunities found.");
            return;
        }

        for (Opportunity opportunity : opportunities) {
            System.out.println("--------------------------------------");
            System.out.println("ID: " + opportunity.getOpportunityId());
            System.out.println("Module: " + opportunity.getModuleCode() + " - " + opportunity.getModuleName());
            System.out.println("Role: " + opportunity.getRoleType());
            System.out.println("Hours: " + opportunity.getWorkingHours());
            System.out.println("Deadline: " + opportunity.getDeadline());
            System.out.println("Skills: " + opportunity.getRequiredSkills());
        }
    }

    private static void handleViewOpportunityDetails(Scanner scanner, OpportunityService opportunityService) {
        System.out.print("Enter opportunity ID: ");
        String id = scanner.nextLine();

        Opportunity opportunity = opportunityService.findById(id);
        if (opportunity == null) {
            System.out.println("Opportunity not found.");
            return;
        }

        System.out.println("--------------------------------------");
        System.out.println("ID: " + opportunity.getOpportunityId());
        System.out.println("Module: " + opportunity.getModuleCode() + " - " + opportunity.getModuleName());
        System.out.println("Role: " + opportunity.getRoleType());
        System.out.println("Hours: " + opportunity.getWorkingHours());
        System.out.println("Deadline: " + opportunity.getDeadline());
        System.out.println("English requirement: " + opportunity.getEnglishRequirement());
        System.out.println("Required skills: " + opportunity.getRequiredSkills());
        System.out.println("Interview required: " + opportunity.isInterviewRequired());
        System.out.println("Screening required: " + opportunity.isScreeningRequired());
        System.out.println("Screening task title: " + opportunity.getScreeningTaskTitle());
        System.out.println("Screening task deadline: " + opportunity.getScreeningTaskDeadline());
        System.out.println("Description: " + opportunity.getDescription());
    }

    private static void handleSubmitApplication(Scanner scanner, ApplicationService applicationService) {
        System.out.print("Application ID: ");
        String applicationId = scanner.nextLine();

        System.out.print("Applicant ID: ");
        String applicantId = scanner.nextLine();

        System.out.print("Opportunity ID: ");
        String opportunityId = scanner.nextLine();

        System.out.print("Motivation statement: ");
        String motivation = scanner.nextLine();

        applicationService.submitApplication(applicationId, applicantId, opportunityId, motivation);
        System.out.println("Application submitted successfully.");
    }

    private static void handleUploadScreening(Scanner scanner, ApplicationService applicationService) {
        System.out.print("Application ID: ");
        String applicationId = scanner.nextLine();

        System.out.print("Screening file name: ");
        String fileName = scanner.nextLine();

        applicationService.uploadScreeningTask(applicationId, fileName);
        System.out.println("Screening task uploaded successfully.");
    }

    private static void handlePostOpportunity(Scanner scanner, OpportunityService opportunityService) {
        Opportunity opportunity = new Opportunity();

        System.out.print("Opportunity ID: ");
        opportunity.setOpportunityId(scanner.nextLine());

        System.out.print("Module code: ");
        opportunity.setModuleCode(scanner.nextLine());

        System.out.print("Module name: ");
        opportunity.setModuleName(scanner.nextLine());

        System.out.print("Role type: ");
        opportunity.setRoleType(scanner.nextLine());

        System.out.print("Working hours: ");
        opportunity.setWorkingHours(scanner.nextLine());

        System.out.print("Application deadline: ");
        opportunity.setDeadline(scanner.nextLine());

        System.out.print("English requirement: ");
        opportunity.setEnglishRequirement(scanner.nextLine());

        System.out.print("Required skills: ");
        opportunity.setRequiredSkills(scanner.nextLine());

        System.out.print("Interview required (true/false): ");
        opportunity.setInterviewRequired(Boolean.parseBoolean(scanner.nextLine()));

        System.out.print("Screening required (true/false): ");
        opportunity.setScreeningRequired(Boolean.parseBoolean(scanner.nextLine()));

        System.out.print("Screening task title: ");
        opportunity.setScreeningTaskTitle(scanner.nextLine());

        System.out.print("Screening task deadline: ");
        opportunity.setScreeningTaskDeadline(scanner.nextLine());

        System.out.print("Description: ");
        opportunity.setDescription(scanner.nextLine());

        opportunityService.postOpportunity(opportunity);
        System.out.println("Opportunity posted successfully.");
    }

    private static void handleReviewApplications(Scanner scanner, ApplicationService applicationService) {
        System.out.print("Enter opportunity ID: ");
        String opportunityId = scanner.nextLine();

        List<ApplicationRecord> applications = applicationService.getApplicationsByOpportunity(opportunityId);

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (ApplicationRecord application : applications) {
            System.out.println("--------------------------------------");
            System.out.println("Application ID: " + application.getApplicationId());
            System.out.println("Applicant ID: " + application.getApplicantId());
            System.out.println("Opportunity ID: " + application.getOpportunityId());
            System.out.println("Status: " + application.getStatus());
            System.out.println("Motivation: " + application.getMotivation());
            System.out.println("Submitted At: " + application.getSubmittedAt());
            System.out.println("Screening File: " + application.getScreeningFileName());
            System.out.println("Screening Submitted At: " + application.getScreeningSubmittedAt());
        }
    }

    private static void handleViewMyApplications(Scanner scanner, ApplicationService applicationService) {
        System.out.print("Enter applicant ID: ");
        String applicantId = scanner.nextLine();

        List<ApplicationRecord> applications = applicationService.getApplicationsByApplicant(applicantId);

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        for (ApplicationRecord application : applications) {
            System.out.println("--------------------------------------");
            System.out.println("Application ID: " + application.getApplicationId());
            System.out.println("Opportunity ID: " + application.getOpportunityId());
            System.out.println("Status: " + application.getStatus());
            System.out.println("Screening File: " + application.getScreeningFileName());
        }
    }
}