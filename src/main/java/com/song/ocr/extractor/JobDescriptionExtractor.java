package com.song.ocr.extractor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JobDescriptionExtractor {

    public Map<String, Integer> extract(String jobDescription) {

        String skillset = "Word,Excel,Powerpoint,Outlook,Access,OneNote" +
                "Filters,folders,mail merge,rules" +
                "Docs,Sheets,Forms,Slides" +
                "WordPress,SEO,Yoast,journalism,technical writing,ghostwriting" +
                "Excel,Google Sheets,OpenOffice,comparative analyses,pivot tables,macros,ink to database,vertical lookups" +
                "Facebook,Twitter,LinkedIn,Instagram,posts,giveaways,customer interaction" +
                "Voicemail,forwarding,hold,recording" +
                "Trello,Slack,Asana,Todoist,Zapier,Basecamp" +
                "Invoicing,expense tracking, accounts payable,reports, payroll,employee time tracking,cash flow management" +
                "Photoshop,Illustrator,InDesign,Acrobat,Free Hand,Corel Draw" +
                "HTML,CSS,Javascript,WordPress,CMS" +
                "Payment Processing,Automated Billing Systems,CRM,Oracle Netsuite,Salesforce,Business Continuity Planning,ERP,SAP, Oracle" +
                "Basic math,arithmetic,statistics,lgebra,trigonometry,geometry,calculus" +
                "Source checking,intellectual property rights,networking,outreach,advanced Google search" +
                "MS Office,Google Drive,spreadsheets,email,PowerPoint,databases,social media,web,enterprise systems" +
                "C#,SQL,Java,C++,HTML,JavaScript,XML,C,Perl,Python,PHP,Objective-C,AJAX,ASP.NET,Ruby" +
                "Assessment,system knowledge,analytical skills,testing,calm mindset,problem-solving,logic,critical thinking skills,collaboration,communication" +
                "Analysis,conceptual skills,brainstorming,decision-making,forecasting,logistics,problem-solving skills,cost-assessment,requirements-gathering" +
                "Task management,prioritization,delegation,task separation,scheduling,risk management,Scrum" +
                "Shooting,framing,writing,editing,compressing,uploading,creating engagement" +
                "Public speaking,PowerPoint,Keynote";

        Map<String, Integer> skillMap = new HashMap<>();

        String [] skills = skillset.split(",");

        String [] words = jobDescription.split(" ");

        Arrays.stream(skills).forEach(skill-> {

            int frequency = 0;
            for(String word : words) {
                if(skill.equalsIgnoreCase(word)) frequency++;
            }

            skillMap.put(skill, frequency);
        });

        return skillMap;
    }
}
