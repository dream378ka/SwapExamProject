package com.scb.exam.springboot.rest;

import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.scb.exam.springboot.bean.SwapResponse;

@RestController
@RequestMapping("/exam")
public class SwapExamRestController {
	
	public static String P_CHAR_SPLIT = "->";
	
	@GetMapping("/swap")
	public ResponseEntity<SwapResponse> findProductById() {
		SwapResponse resultObj = new SwapResponse();

		//Output
		resultObj.setStatus("01");
		resultObj.setDescription("Parameter linklist is null");
		
		return ResponseEntity.ok().body(resultObj);
	}
	
	@GetMapping("/swap/{linklist}")
	public ResponseEntity<SwapResponse> findProductById(@PathVariable String linklist) {
		SwapResponse resultObj = new SwapResponse();
		byte[] dataDecriptArr = null;
		
		resultObj.getInput().setDataEncode(linklist);
		//Check Input
		try {
			dataDecriptArr = Base64.getDecoder().decode(linklist.getBytes());
		}catch(Exception e) {
			resultObj.setStatus("02");
			resultObj.setDescription("Parameter linklist cannot decode data");
			return ResponseEntity.ok().body(resultObj);
		}
		String decodedString = new String(dataDecriptArr);
		resultObj.getInput().setDataDecode(decodedString);
		
		//Process
		//Output
		String outputString = swapDualData(decodedString);
		resultObj.getOutput().setDataDecode(outputString);
		resultObj.getOutput().setDataEncode(Base64.getEncoder().encodeToString(outputString.getBytes()));
		resultObj.setStatus("00");
		resultObj.setDescription("");
		
		return ResponseEntity.ok().body(resultObj);
	}
	
	public String swapDualData(String param) {
		String result = null;
		if(param!=null && !param.trim().isEmpty()) {
			String[] paramArr = param.split(P_CHAR_SPLIT);

			//Process
			int length = paramArr.length;
			int pairInt = length/2;
			
			if(pairInt>0) {
				for(int i=0; i<pairInt; i++) {
					int position = i*2;
					String temp = paramArr[position];
					paramArr[position] = paramArr[position+1];
					paramArr[position+1] = temp;
				}
			}
			
			//After
			result = mergeDataArr(paramArr);
		}
		
		return result;
	}
	
	public String mergeDataArr(String[] param) {
		StringBuilder resultBuilder = new StringBuilder();
		if(param!=null && param.length > 0) {
			boolean first = true;
			for (String p : param) {
				if(!first) {
					resultBuilder.append(P_CHAR_SPLIT);
				}else {
					first = false;
				}
				resultBuilder.append(p);
			}
		}
		return resultBuilder.toString();
	}
}