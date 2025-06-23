
package Pages;

import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.*;

// This is a test comment
public class OTPMailosaur {
	
	//Mail access to get the OTP
    
	private final String apiKey = "MdPFsbue28eqIdDpRmcfUyhQdjzlwDZz";
	private final String serverId = "eokwm7zd";
	private final String serverDomain = "eokwm7zd.mailosaur.net";
    private final String fromEmailID="prabandhak.reverie@reverieinc.com";
    String otp;
    private final MailosaurClient mailosaur;
    
    public OTPMailosaur()
    {
       this.mailosaur=new MailosaurClient(apiKey);
    }
        
    public String testEmailExample(String targetEmail) throws IOException, MailosaurException
    {	
        // Set up search params and criteria
        MessageSearchParams params = new MessageSearchParams();
        params.withServer(serverId);
        
      
        SearchCriteria criteria = new SearchCriteria();
       // System.out.println();
        criteria.withSentTo(targetEmail);
        criteria.withSentFrom(fromEmailID);
        
        // Get the message

        Message message = mailosaur.messages().get(params, criteria);
        
        String body = message.text().body();
        System.out.println(body);
        
        // Define a regex pattern for a 4-digit OTP (since your OTP is 4 digits)

        Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
        Matcher match =pattern.matcher(body);
        if(match.find())
        {
        
        otp=match.group(0);
        System.out.println(otp);
        }
        else
        {
        	System.out.println("OTP not found in the body");
        }
     
        return otp;
   }

 public String getcorrectEmailID() 
 {
			//TODO Auto-generated method stub
			 String randomEmailID= "user" + System.currentTimeMillis();
			String dummyemail= (randomEmailID + "@" + serverDomain);
			return dummyemail;
			
 }

public CharSequence MobileNumberGenerator() 
{
			// TODO Auto-generated method stub
            Random random = new Random();
			
	        // First digit (typically 7, 8, or 9 for India)
			int FirstDigit = 7+random.nextInt(3);
			
	        // Generate the remaining 9 digits
	        long remainingDigits = 100000000L + (long)(random.nextDouble() * 900000000L);
            String MobileNumber = FirstDigit+ String.valueOf(remainingDigits);
            return MobileNumber;		
 }
		
public String getWelcomeEmail(String targetEmail) throws IOException, MailosaurException
{
			 MessageSearchParams params = new MessageSearchParams();
		     params.withServer(serverId);
		     
		     SearchCriteria criteria = new SearchCriteria();
		     criteria.withSentTo(targetEmail);
		     criteria.withSubject("Welcome to Prabandhak");
		     Message message = mailosaur.messages().get(params, criteria);
             String MessageSub = message.subject();
			 return MessageSub;
}

public String getPrivacyPolicyEmail(String loggedinEmail) throws IOException, MailosaurException
{
	MessageSearchParams params = new MessageSearchParams();
    params.withServer(serverId);
    SearchCriteria criteria = new SearchCriteria();
    criteria.withSentTo(loggedinEmail);
    criteria.withSubject("Prabandhak Privacy Policy");
    Message message = mailosaur.messages().get(params, criteria);
    String MessageSub = message.subject();	
	return MessageSub;
	
}

public String getEULAEmail(String loggedinEmail) throws IOException, MailosaurException
{
	MessageSearchParams params = new MessageSearchParams();
    params.withServer(serverId);
    SearchCriteria criteria = new SearchCriteria();
    criteria.withSentTo(loggedinEmail);
    criteria.withSubject("End user license agreement");
    Message message = mailosaur.messages().get(params, criteria);
    String MessageSub = message.subject();	
	return MessageSub;
	
}
    
}




