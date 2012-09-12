package org.sharpsw.ejbcacli.service.mocks;

import java.util.List;

import org.sharpsw.ejbcacli.AlreadyRevokedException_Exception;
import org.sharpsw.ejbcacli.ApprovalException_Exception;
import org.sharpsw.ejbcacli.ApprovalRequestExecutionException_Exception;
import org.sharpsw.ejbcacli.ApprovalRequestExpiredException_Exception;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.Certificate;
import org.sharpsw.ejbcacli.CertificateExpiredException_Exception;
import org.sharpsw.ejbcacli.CertificateResponse;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.HardTokenDataWS;
import org.sharpsw.ejbcacli.HardTokenDoesntExistsException_Exception;
import org.sharpsw.ejbcacli.HardTokenExistsException_Exception;
import org.sharpsw.ejbcacli.IllegalQueryException_Exception;
import org.sharpsw.ejbcacli.KeyStore;
import org.sharpsw.ejbcacli.MultipleMatchException_Exception;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.NotFoundException_Exception;
import org.sharpsw.ejbcacli.PublisherException_Exception;
import org.sharpsw.ejbcacli.RevokeStatus;
import org.sharpsw.ejbcacli.SignRequestException_Exception;
import org.sharpsw.ejbcacli.TokenCertificateRequestWS;
import org.sharpsw.ejbcacli.TokenCertificateResponseWS;
import org.sharpsw.ejbcacli.UserDataSourceException_Exception;
import org.sharpsw.ejbcacli.UserDataSourceVOWS;
import org.sharpsw.ejbcacli.UserDataVOWS;
import org.sharpsw.ejbcacli.UserDoesntFullfillEndEntityProfile_Exception;
import org.sharpsw.ejbcacli.UserMatch;
import org.sharpsw.ejbcacli.WaitingForApprovalException_Exception;

public class EjbcaWSMock implements EjbcaWS {

	@Override
	public void caCertResponse(String arg0, byte[] arg1, List<byte[]> arg2,
			String arg3) throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			WaitingForApprovalException_Exception {

	}

	@Override
	public byte[] caRenewCertRequest(String arg0, List<byte[]> arg1,
			boolean arg2, boolean arg3, boolean arg4, String arg5)
			throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			WaitingForApprovalException_Exception {
		return null;
	}

	@Override
	public CertificateResponse certificateRequest(UserDataVOWS arg0,
			String arg1, int arg2, String arg3, String arg4)
			throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception,
			UserDoesntFullfillEndEntityProfile_Exception,
			WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RevokeStatus checkRevokationStatus(String arg0, String arg1)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCRL(String arg0) throws ApprovalException_Exception,
			ApprovalRequestExpiredException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CertificateResponse crmfRequest(String arg0, String arg1,
			String arg2, String arg3, String arg4)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void customLog(int arg0, String arg1, String arg2, String arg3,
			Certificate arg4, String arg5)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Certificate> cvcRequest(String arg0, String arg1, String arg2)
			throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception,
			CertificateExpiredException_Exception, EjbcaException_Exception,
			NotFoundException_Exception, SignRequestException_Exception,
			UserDoesntFullfillEndEntityProfile_Exception,
			WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUserDataFromSource(List<String> arg0, String arg1,
			boolean arg2) throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception, MultipleMatchException_Exception,
			UserDataSourceException_Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void editUser(UserDataVOWS arg0) throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			UserDoesntFullfillEndEntityProfile_Exception,
			WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsHardToken(String arg0) throws EjbcaException_Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserDataSourceVOWS> fetchUserData(List<String> arg0, String arg1)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception, UserDataSourceException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Certificate> findCerts(String arg0, boolean arg1)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDataVOWS> findUser(UserMatch arg0)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception, IllegalQueryException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TokenCertificateResponseWS> genTokenCertificates(
			UserDataVOWS arg0, List<TokenCertificateRequestWS> arg1,
			HardTokenDataWS arg2, boolean arg3, boolean arg4)
			throws ApprovalException_Exception,
			ApprovalRequestExecutionException_Exception,
			ApprovalRequestExpiredException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			HardTokenExistsException_Exception,
			UserDoesntFullfillEndEntityProfile_Exception,
			WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NameAndId> getAuthorizedEndEntityProfiles()
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NameAndId> getAvailableCAs()
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NameAndId> getAvailableCAsInProfile(int arg0)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NameAndId> getAvailableCertificateProfiles(int arg0)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Certificate getCertificate(String arg0, String arg1)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEjbcaVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HardTokenDataWS getHardTokenData(String arg0, boolean arg1,
			boolean arg2) throws ApprovalRequestExecutionException_Exception,
			ApprovalRequestExpiredException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			HardTokenDoesntExistsException_Exception,
			NotFoundException_Exception, WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HardTokenDataWS> getHardTokenDatas(String arg0, boolean arg1,
			boolean arg2) throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Certificate> getLastCAChain(String arg0)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Certificate> getLastCertChain(String arg0)
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPublisherQueueLength(String arg0)
			throws EjbcaException_Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isApproved(int arg0) throws ApprovalException_Exception,
			ApprovalRequestExpiredException_Exception, EjbcaException_Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAuthorized(String arg0) throws EjbcaException_Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void keyRecoverNewest(String arg0)
			throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception, WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CertificateResponse pkcs10Request(String arg0, String arg1,
			String arg2, String arg3, String arg4)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyStore pkcs12Req(String arg0, String arg1, String arg2,
			String arg3, String arg4)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void republishCertificate(String arg0, String arg1)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			PublisherException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void revokeCert(String arg0, String arg1, int arg2)
			throws AlreadyRevokedException_Exception,
			ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception, WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void revokeToken(String arg0, int arg1)
			throws AlreadyRevokedException_Exception,
			ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception, WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void revokeUser(String arg0, int arg1, boolean arg2)
			throws AlreadyRevokedException_Exception,
			ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception, WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public KeyStore softTokenRequest(UserDataVOWS arg0, String arg1,
			String arg2, String arg3) throws ApprovalException_Exception,
			AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception,
			UserDoesntFullfillEndEntityProfile_Exception,
			WaitingForApprovalException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CertificateResponse spkacRequest(String arg0, String arg1,
			String arg2, String arg3, String arg4)
			throws AuthorizationDeniedException_Exception,
			CADoesntExistsException_Exception, EjbcaException_Exception,
			NotFoundException_Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
