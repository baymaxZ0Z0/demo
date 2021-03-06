/*
 * Copyright 2005-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 **************************************************************************
 * Date:       	    by:    		    Reason:   
 * 
 * 2015-8-12     	Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.example.Services.weedFs;

/**
 * Description：<br>
 * HTTP Method
 * 
 * @author simon
 * @date 2015-8-12
 * @version v1.0.0
 */
public enum EHttpMethod {
	GET("GET"),
	POST("POST"),
	DELETE("DELETE"),
	
	;
	private String value;
	private EHttpMethod(String value) {
		this.value = value;
	}
	public String value() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}


