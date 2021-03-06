package io.trxplorer.protocol.parser;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.tron.api.GrpcAPI.AccountResourceMessage;
import org.tron.core.Wallet;
import org.tron.protos.Protocol.Account;
import org.tron.protos.Protocol.Account.Frozen;

import io.trxplorer.protocol.model.core.AccountModel;
import io.trxplorer.protocol.model.core.AccountResourceModel;

public class AccountParser {

	
	public static AccountModel parse(Account account) {
		
		String address =  Wallet.encode58Check(account.getAddress().toByteArray());
		String accountName = account.getAccountName().toStringUtf8();
		
		if (StringUtils.isBlank(accountName)) {
			accountName = null;
		}
		
		long balance = account.getBalance();
		long createTime = account.getCreateTime();
		long netUsage = account.getNetUsage();
		
		
		AccountModel result = new AccountModel();
		result.setAddress(address);
		result.setAccountName(accountName);
		result.setBalance(balance);
		result.setCreateTime(createTime);
		result.setNetUsage(netUsage);
		result.setAssetIssuedId(account.getAssetIssuedID().toStringUtf8());
		
		HashMap<String,BigDecimal> newAssetMap = new HashMap<>();
		for(String key:account.getAssetV2Map().keySet()) {
			
			newAssetMap.put(key, new BigDecimal(account.getAssetV2Map().get(key)));
			
		}
		result.setAssetMap(newAssetMap);
		
		
		HashMap<String, Long> votes = new HashMap<>();
		
		for(org.tron.protos.Protocol.Vote vote:account.getVotesList()) {
			String voteAddress =  Wallet.encode58Check(vote.getVoteAddress().toByteArray());	
			votes.put(voteAddress, vote.getVoteCount());
		}
		
		
		result.setVotes(votes);
		if (account.getAccountResource()!=null && account.getAccountResource().getFrozenBalanceForEnergy()!=null) {
			result.setFrozenBalance(account.getAccountResource().getFrozenBalanceForEnergy().getFrozenBalance());
		}
		

		if (account.getFrozenList()!=null && account.getFrozenList().size()>0) {
			
			long totalFrozen = result.getFrozenBalance(); 
			
			for(Frozen f:account.getFrozenList()) {
				totalFrozen = totalFrozen + f.getFrozenBalance();
			}
			result.setFrozenBalance(totalFrozen);
			result.setFrozenBalanceExpiration(account.getFrozen(0).getExpireTime());
		}
		
		return result;
	}
	
	public static AccountResourceModel parse(AccountResourceMessage accountResource) {
		
		AccountResourceModel result  = new AccountResourceModel();
		
		result.setNetLimit(accountResource.getNetLimit());
		result.setNetUsed(accountResource.getNetUsed());
		result.setFreeNetLimit(accountResource.getFreeNetLimit());
		result.setFreeNetUsed(accountResource.getFreeNetUsed());
		result.setEnergyLimit(accountResource.getEnergyLimit());
		result.setEnergyUsed(accountResource.getEnergyUsed());
		
		return result;
	}
	
}
