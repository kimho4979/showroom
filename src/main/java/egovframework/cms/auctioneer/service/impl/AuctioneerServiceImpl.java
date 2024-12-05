package egovframework.cms.auctioneer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.cms.auctioneer.service.AuctioneerService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("auctioneerService")
public class AuctioneerServiceImpl implements AuctioneerService {

	@Resource(name = "auctioneerMapper")
	private AuctioneerMapper auctioneerMapper;

	
	@Override
	public List<EgovMap> getAuctioneerList(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.getAuctioneerList(paramMap);
	}


	@Override
	public int getAuctioneerListCnt(Map<String, Object> paramMap)
			throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.getAuctioneerListCnt(paramMap);
	}


	@Override
	public int idValidCheck(String id) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.idValidCheck(id);
	}


	@Override
	public int insert(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.insert(paramMap);
	}
	
	
	@Override
	public EgovMap getAuctioneer(Long memberId) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.getAuctioneer(memberId);
	}


	@Override
	public int update(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.update(paramMap);
	}


	@Override
	public int delete(Long memberId) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.delete(memberId);
	}


	@Override
	public EgovMap getAucMember(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return auctioneerMapper.getAucMember(paramMap);
	}
	
}
