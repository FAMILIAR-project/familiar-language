var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 QualityOfService: {
				 ComputerLoad: 10000 ,
				 ResponseTime: 10001 ,
				 Quality: {
						 Sensitivity: 10002 ,
						 Precision: {
							 LessPrecision: 10003,
						},
				},
		},
		 Task: {
				 Counting: 10004 ,
				 Tracking: 10005 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10006,
				},
		},
		 Scene: {
			 Environment: {
				 LightingConditions: {
						 Indoors: 10007 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10008 ,
									 Night: 10009 ,
							},
						},
				},
				 Noise: {
					 LightingVariation: {
							 Flashes: 10010 ,
							 HeadLight: 10011 ,
							 Shadows: 10012 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10013 ,
							 SeaOceanWaves: 10014 ,
							 VegetationMovement: 10015 ,
					},
				},
				 LightingType: {
						 ArtificialLight: 10016 ,
						 NaturalLight: 10017 ,
				},
			},
			 APrioriKnowledge: {
				 BehaviourLibrary: 10018,
				 DeploymentArchitecture: {
					 Sensor: {
						 Camera: {
							 CameraType: {
									 BlackAndWhite: 10019 ,
									 Color: 10020 ,
									 Infrared: 10021 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10022,
									},
									 LowFrameRate: 10023 ,
									 IntermediateFrameRate: 10024 ,
							},
							 Resolution: 10025,
							 FieldOfView: {
									 Large: 10026 ,
									 Narrow: 10027 ,
							},
							 DepthOfField: 10028,
						},
						 SensorProperty: {
								 MonoSensor: 10029 ,
								 MultiSensor: 10030 ,
						},
						 SensorView: {
								 SideView: 10031 ,
								 TopView: 10032 ,
								 NormalView: 10033 ,
						},
					},
					 Server: 10034,
					 ClientDefined: 10035,
					 ProgrammerDefined: 10036,
				},
				 Scenery3DModel: 10037,
			},
		},
		 ObjectOfInterest: {
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10038 ,
							 SelfPropelled: 10039 ,
					},
					 Immovable: 10040 ,
			},
			 Cardinality: {
					 SingleObject: 10041 ,
					 GroupOfObjects: 10042 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10043,
					},
					 Manufactured: {
						 Vehicle: 10044,
					},
			},
		},
	},
	 VSPlatform: {
		 ReferenceImageUpdating: 10045,
		 TrackingDataFusion: 10046,
		 SegmentationModule: {
			 SegmFineTune: 10047,
			 Threshold: {
					 LowThreshold: 10048 ,
					 MidThreshold: 10049 ,
					 HighThreshold: 10050 ,
			},
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10051 ,
						 WithMask: 10052 ,
						 Fast: 10053 ,
				},
				 GridStep: {
						 HighGridStep: 10054 ,
						 LowGridStep: 10055 ,
				},
			},
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10056 ,
						 Region: 10057 ,
				},
				 ShadowElimination: 10058,
				 ColorType: {
						 GreyLevel: 10059 ,
						 SystemColor: 10060 ,
				},
			},
		},
		 ScenarioRecognition: 10061,
		 ClassificationModule: {
			 Model: {
					 ModelType: {
							 OmegaModel: 10062 ,
							 ThreeDModel: 10063 ,
					},
					 Ellipse: 10064 ,
					 Shape: 10065 ,
					 Parallelepiped: 10066 ,
					 GravityCenter: 10067 ,
			},
			 Split: {
					 SplitStandard: 10068 ,
					 SplitGravityCenter: 10069 ,
			},
			 Merge: {
					 FusionGravityCenter: 10070 ,
					 FusionStandard: 10071 ,
			},
			 ClassificationStandard: {
				 Density: 10072,
			},
			 Contour: 10073,
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10074 ,
				 FastClustering: 10075 ,
				 KMeansClustering: 10076 ,
		},
		 ImageAcquisition: 10077,
		 FrameToFrameTracking: 10078,
		 LongTermTracking: 10079,
	},
}

};

