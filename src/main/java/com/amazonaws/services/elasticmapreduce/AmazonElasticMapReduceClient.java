/*
 * Copyright 2010-2011 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.elasticmapreduce;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.*;
import com.amazonaws.auth.AWS3Signer;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.QueryStringSigner;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.http.StaxResponseHandler;
import com.amazonaws.http.DefaultErrorResponseHandler;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.StandardErrorUnmarshaller;

import com.amazonaws.services.elasticmapreduce.model.*;
import com.amazonaws.services.elasticmapreduce.model.transform.*;


/**
 * Client for accessing AmazonElasticMapReduce.  All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * <p>
 * <p>
 * This is the <i>Amazon Elastic MapReduce API Reference</i> . This
 * guide provides descriptions and samples of the Amazon Elastic
 * MapReduce APIs.
 * </p>
 * <p>
 * Amazon Elastic MapReduce is a web service that makes it easy to
 * process large amounts of data efficiently. Elastic MapReduce uses
 * Hadoop processing combined with several AWS products to do tasks such
 * as web indexing, data mining, log file analysis, machine learning,
 * scientific simulation, and data warehousing.
 * </p>
 */
public class AmazonElasticMapReduceClient extends AmazonWebServiceClient implements AmazonElasticMapReduce {

    /**
     * The AWS credentials (access key ID and secret key) to use when
     * authenticating with AWS services.
     */
    private AWSCredentials awsCredentials;

    /**
     * List of exception unmarshallers for all AmazonElasticMapReduce exceptions.
     */
    protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers;

    
    /** AWS signer for authenticating requests. */
    private QueryStringSigner signer;


    /**
     * Constructs a new client to invoke service methods on
     * AmazonElasticMapReduce using the specified AWS account credentials.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     */
    public AmazonElasticMapReduceClient(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonElasticMapReduce using the specified AWS account credentials
     * and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonElasticMapReduce
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonElasticMapReduceClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentials = awsCredentials;

        exceptionUnmarshallers = new ArrayList<Unmarshaller<AmazonServiceException, Node>>();
        exceptionUnmarshallers.add(new InternalServerErrorExceptionUnmarshaller());
        
        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        setEndpoint("elasticmapreduce.amazonaws.com");

        signer = new QueryStringSigner();

        HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandlers.addAll(chainFactory.newRequestHandlerChain(
                "/com/amazonaws/services/elasticmapreduce/request.handlers"));
    }

    
    /**
     * <p>
     * AddInstanceGroups adds an instance group to a running cluster.
     * </p>
     *
     * @param addInstanceGroupsRequest Container for the necessary parameters
     *           to execute the AddInstanceGroups service method on
     *           AmazonElasticMapReduce.
     * 
     * @return The response from the AddInstanceGroups service method, as
     *         returned by AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public AddInstanceGroupsResult addInstanceGroups(AddInstanceGroupsRequest addInstanceGroupsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<AddInstanceGroupsRequest> request = new AddInstanceGroupsRequestMarshaller().marshall(addInstanceGroupsRequest);
        return invoke(request, new AddInstanceGroupsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * AddJobFlowSteps adds new steps to a running job flow. A maximum of
     * 256 steps are allowed in each job flow.
     * </p>
     * <p>
     * If your job flow is long-running (such as a Hive data warehouse) or
     * complex, you may require more than 256 steps to process your data. You
     * can bypass the 256-step limitation in various ways, including using
     * the SSH shell to connect to the master node and submitting queries
     * directly to the software running on the master node, such as Hive and
     * Hadoop. For more information on how to do this, go to <a
     * .com/ElasticMapReduce/latest/DeveloperGuide/AddMoreThan256Steps.html">
     * Add More than 256 Steps to a Job Flow </a> in the <i>Amazon Elastic
     * MapReduce Developer's Guide</i> .
     * </p>
     * <p>
     * A step specifies the location of a JAR file stored either on the
     * master node of the job flow or in Amazon S3. Each step is performed by
     * the main function of the main class of the JAR file. The main class
     * can be specified either in the manifest of the JAR or by using the
     * MainFunction parameter of the step.
     * </p>
     * <p>
     * Elastic MapReduce executes each step in the order listed. For a step
     * to be considered complete, the main function must exit with a zero
     * exit code and all Hadoop jobs started while the step was running must
     * have completed and run successfully.
     * </p>
     * <p>
     * You can only add steps to a job flow that is in one of the following
     * states: STARTING, BOOTSTRAPPING, RUNNING, or WAITING.
     * </p>
     *
     * @param addJobFlowStepsRequest Container for the necessary parameters
     *           to execute the AddJobFlowSteps service method on
     *           AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void addJobFlowSteps(AddJobFlowStepsRequest addJobFlowStepsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<AddJobFlowStepsRequest> request = new AddJobFlowStepsRequestMarshaller().marshall(addJobFlowStepsRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * TerminateJobFlows shuts a list of job flows down. When a job flow is
     * shut down, any step not yet completed is canceled and the EC2
     * instances on which the job flow is running are stopped. Any log files
     * not already saved are uploaded to Amazon S3 if a LogUri was specified
     * when the job flow was created.
     * </p>
     *
     * @param terminateJobFlowsRequest Container for the necessary parameters
     *           to execute the TerminateJobFlows service method on
     *           AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void terminateJobFlows(TerminateJobFlowsRequest terminateJobFlowsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<TerminateJobFlowsRequest> request = new TerminateJobFlowsRequestMarshaller().marshall(terminateJobFlowsRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * DescribeJobFlows returns a list of job flows that match all of the
     * supplied parameters. The parameters can include a list of job flow
     * IDs, job flow states, and restrictions on job flow creation date and
     * time.
     * </p>
     * <p>
     * Regardless of supplied parameters, only job flows created within the
     * last two months are returned.
     * </p>
     * <p>
     * If no parameters are supplied, then job flows matching either of the
     * following criteria are returned:
     * </p>
     * 
     * <ul>
     * <li>Job flows created and completed in the last two weeks</li>
     * <li> Job flows created within the last two months that are in one of
     * the following states: <code>RUNNING</code> ,
     * <code>WAITING</code> ,
     * <code>SHUTTING_DOWN</code> ,
     * 
     * <code>STARTING</code> </li>
     * 
     * </ul>
     * <p>
     * Amazon Elastic MapReduce can return a maximum of 512 job flow
     * descriptions.
     * </p>
     *
     * @param describeJobFlowsRequest Container for the necessary parameters
     *           to execute the DescribeJobFlows service method on
     *           AmazonElasticMapReduce.
     * 
     * @return The response from the DescribeJobFlows service method, as
     *         returned by AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeJobFlowsResult describeJobFlows(DescribeJobFlowsRequest describeJobFlowsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeJobFlowsRequest> request = new DescribeJobFlowsRequestMarshaller().marshall(describeJobFlowsRequest);
        return invoke(request, new DescribeJobFlowsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * SetTerminationProtection locks a job flow so the Amazon EC2 instances
     * in the cluster cannot be terminated by user intervention, an API call,
     * or in the event of a job-flow error. The cluster still terminates upon
     * successful completion of the job flow. Calling
     * SetTerminationProtection on a job flow is analogous to calling the
     * Amazon EC2 DisableAPITermination API on all of the EC2 instances in a
     * cluster.
     * </p>
     * <p>
     * SetTerminationProtection is used to prevent accidental termination of
     * a job flow and to ensure that in the event of an error, the instances
     * will persist so you can recover any data stored in their ephemeral
     * instance storage.
     * </p>
     * <p>
     * To terminate a job flow that has been locked by setting
     * SetTerminationProtection to <code>true</code> ,
     * you must first unlock the job flow by a subsequent call to
     * SetTerminationProtection in which you set the value to
     * <code>false</code> .
     * </p>
     * <p>
     * For more information, go to <a
     * cMapReduce/latest/DeveloperGuide/UsingEMR_TerminationProtection.html">
     * Protecting a Job Flow from Termination </a> in the <i>Amazon Elastic
     * MapReduce Developer's Guide.</i>
     * </p>
     *
     * @param setTerminationProtectionRequest Container for the necessary
     *           parameters to execute the SetTerminationProtection service method on
     *           AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void setTerminationProtection(SetTerminationProtectionRequest setTerminationProtectionRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<SetTerminationProtectionRequest> request = new SetTerminationProtectionRequestMarshaller().marshall(setTerminationProtectionRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * RunJobFlow creates and starts running a new job flow. The job flow
     * will run the steps specified. Once the job flow completes, the cluster
     * is stopped and the HDFS partition is lost. To prevent loss of data,
     * configure the last step of the job flow to store results in Amazon S3.
     * If the JobFlowInstancesDetail <code>KeepJobFlowAliveWhenNoSteps</code>
     * parameter is set to <code>TRUE</code> , the job flow will transition
     * to the WAITING state rather than shutting down once the steps have
     * completed.
     * </p>
     * <p>
     * For additional protection, you can set the JobFlowInstancesDetail
     * <code>TerminationProtected</code> parameter to <code>TRUE</code> to
     * lock the job flow and prevent it from being terminated by API call,
     * user intervention, or in the event of a job flow error.
     * </p>
     * <p>
     * A maximum of 256 steps are allowed in each job flow.
     * </p>
     * <p>
     * If your job flow is long-running (such as a Hive data warehouse) or
     * complex, you may require more than 256 steps to process your data. You
     * can bypass the 256-step limitation in various ways, including using
     * the SSH shell to connect to the master node and submitting queries
     * directly to the software running on the master node, such as Hive and
     * Hadoop. For more information on how to do this, go to <a
     * .com/ElasticMapReduce/latest/DeveloperGuide/AddMoreThan256Steps.html">
     * Add More than 256 Steps to a Job Flow </a> in the <i>Amazon Elastic
     * MapReduce Developer's Guide</i> .
     * </p>
     * <p>
     * For long running job flows, we recommend that you periodically store
     * your results.
     * </p>
     *
     * @param runJobFlowRequest Container for the necessary parameters to
     *           execute the RunJobFlow service method on AmazonElasticMapReduce.
     * 
     * @return The response from the RunJobFlow service method, as returned
     *         by AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public RunJobFlowResult runJobFlow(RunJobFlowRequest runJobFlowRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<RunJobFlowRequest> request = new RunJobFlowRequestMarshaller().marshall(runJobFlowRequest);
        return invoke(request, new RunJobFlowResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * ModifyInstanceGroups modifies the number of nodes and configuration
     * settings of an instance group. The input parameters include the new
     * target instance count for the group and the instance group ID. The
     * call will either succeed or fail atomically.
     * </p>
     *
     * @param modifyInstanceGroupsRequest Container for the necessary
     *           parameters to execute the ModifyInstanceGroups service method on
     *           AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void modifyInstanceGroups(ModifyInstanceGroupsRequest modifyInstanceGroupsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ModifyInstanceGroupsRequest> request = new ModifyInstanceGroupsRequestMarshaller().marshall(modifyInstanceGroupsRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * DescribeJobFlows returns a list of job flows that match all of the
     * supplied parameters. The parameters can include a list of job flow
     * IDs, job flow states, and restrictions on job flow creation date and
     * time.
     * </p>
     * <p>
     * Regardless of supplied parameters, only job flows created within the
     * last two months are returned.
     * </p>
     * <p>
     * If no parameters are supplied, then job flows matching either of the
     * following criteria are returned:
     * </p>
     * 
     * <ul>
     * <li>Job flows created and completed in the last two weeks</li>
     * <li> Job flows created within the last two months that are in one of
     * the following states: <code>RUNNING</code> ,
     * <code>WAITING</code> ,
     * <code>SHUTTING_DOWN</code> ,
     * 
     * <code>STARTING</code> </li>
     * 
     * </ul>
     * <p>
     * Amazon Elastic MapReduce can return a maximum of 512 job flow
     * descriptions.
     * </p>
     * 
     * @return The response from the DescribeJobFlows service method, as
     *         returned by AmazonElasticMapReduce.
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeJobFlowsResult describeJobFlows() throws AmazonServiceException, AmazonClientException {
        return describeJobFlows(new DescribeJobFlowsRequest());
    }
    
    /**
     * <p>
     * ModifyInstanceGroups modifies the number of nodes and configuration
     * settings of an instance group. The input parameters include the new
     * target instance count for the group and the instance group ID. The
     * call will either succeed or fail atomically.
     * </p>
     * 
     * @throws InternalServerErrorException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonElasticMapReduce indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void modifyInstanceGroups() throws AmazonServiceException, AmazonClientException {
        modifyInstanceGroups(new ModifyInstanceGroupsRequest());
    }
    


    /**
     * Returns additional metadata for a previously executed successful, request, typically used for
     * debugging issues where a service isn't acting as expected.  This data isn't considered part
     * of the result data returned by an operation, so it's available through this separate,
     * diagnostic interface.
     * <p>
     * Response metadata is only cached for a limited period of time, so if you need to access
     * this extra diagnostic information for an executed request, you should use this method
     * to retrieve it as soon as possible after executing the request.
     *
     * @param request
     *            The originally executed request
     *
     * @return The response metadata for the specified request, or null if none
     *         is available.
     */
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) {
        return client.getResponseMetadataForRequest(request);
    }

    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, Unmarshaller<X, StaxUnmarshallerContext> unmarshaller) {
        request.setEndpoint(endpoint);
        for (Entry<String, String> entry : request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

        // Apply any additional service specific request handlers that need to be run
        if (requestHandlers != null) {
            for (RequestHandler requestHandler : requestHandlers) {
                requestHandler.beforeRequest(request);
            }
        }

        if (request.getOriginalRequest().getRequestCredentials() != null) {
	        signer.sign(request, request.getOriginalRequest().getRequestCredentials());
        } else {
    	    signer.sign(request, awsCredentials);
        }

        
        StaxResponseHandler<X> responseHandler = new StaxResponseHandler<X>(unmarshaller);
        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

        ExecutionContext executionContext = createExecutionContext();
        return (X)client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }
}
        