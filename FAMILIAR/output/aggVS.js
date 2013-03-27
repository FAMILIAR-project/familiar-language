var flare = {
aggVS: {
	 VSApplicationRequirement: {
		 Task: {
				 Counting: 10000 ,
				 Tracking: 10001 ,
				 BehaviourRecognition: {
					 IntrusionDetection: 10002,
				},
		},
		 QualityOfService: {
				 ComputerLoad: 10003 ,
				 ResponseTime: 10004 ,
				 Quality: {
						 Sensitivity: 10005 ,
						 Precision: {
							 LessPrecision: 10006,
						},
				},
		},
		 ObjectOfInterest: {
			 Sort: {
					 Natural: {
						 Person: 10007,
					},
					 Manufactured: {
						 Vehicle: 10008,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10009 ,
							 SelfPropelled: 10010 ,
					},
					 Immovable: 10011 ,
			},
			 Cardinality: {
					 SingleObject: 10012 ,
					 GroupOfObjects: 10013 ,
			},
		},
		 Scene: {
			 Environment: {
				 LightingConditions: {
						 Indoors: 10014 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10015 ,
									 Night: 10016 ,
							},
						},
				},
				 Noise: {
					 LightingVariation: {
							 Flashes: 10017 ,
							 HeadLight: 10018 ,
							 Shadows: 10019 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10020 ,
							 SeaOceanWaves: 10021 ,
							 VegetationMovement: 10022 ,
					},
				},
				 LightingType: {
						 ArtificialLight: 10023 ,
						 NaturalLight: 10024 ,
				},
			},
			 APrioriKnowledge: {
				 DeploymentArchitecture: {
					 ClientDefined: 10025,
					 ProgrammerDefined: 10026,
					 Server: 10027,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10028 ,
								 MultiSensor: 10029 ,
						},
						 Camera: {
							 Resolution: 10030,
							 CameraType: {
									 BlackAndWhite: 10031 ,
									 Color: 10032 ,
									 Infrared: 10033 ,
							},
							 DepthOfField: 10034,
							 FieldOfView: {
									 Large: 10035 ,
									 Narrow: 10036 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10037,
									},
									 LowFrameRate: 10038 ,
									 IntermediateFrameRate: 10039 ,
							},
						},
						 SensorView: {
								 SideView: 10040 ,
								 TopView: 10041 ,
								 NormalView: 10042 ,
						},
					},
				},
				 BehaviourLibrary: 10043,
				 Scenery3DModel: 10044,
			},
		},
	},
	 VSPlatform: {
		 FrameToFrameTracking: 10045,
		 SegmentationModule: {
			 SegmFineTune: 10046,
			 KernelFunction: {
				 ColorType: {
						 GreyLevel: 10047 ,
						 SystemColor: 10048 ,
				},
				 ShadowElimination: 10049,
				 ObjectAspect: {
						 Edge: 10050 ,
						 Region: 10051 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10052 ,
					 MidThreshold: 10053 ,
					 HighThreshold: 10054 ,
			},
			 TraversalAlgorithm: {
				 GridStep: {
						 HighGridStep: 10055 ,
						 LowGridStep: 10056 ,
				},
				 TraversalParameters: {
						 WithWindow: 10057 ,
						 WithMask: 10058 ,
						 Fast: 10059 ,
				},
			},
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10060 ,
				 FastClustering: 10061 ,
				 KMeansClustering: 10062 ,
		},
		 ScenarioRecognition: 10063,
		 ImageAcquisition: 10064,
		 ClassificationModule: {
			 Contour: 10065,
			 Merge: {
					 FusionGravityCenter: 10066 ,
					 FusionStandard: 10067 ,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10068 ,
							 ThreeDModel: 10069 ,
					},
					 Ellipse: 10070 ,
					 Shape: 10071 ,
					 Parallelepiped: 10072 ,
					 GravityCenter: 10073 ,
			},
			 ClassificationStandard: {
				 Density: 10074,
			},
			 Split: {
					 SplitStandard: 10075 ,
					 SplitGravityCenter: 10076 ,
			},
		},
		 TrackingDataFusion: 10077,
		 ReferenceImageUpdating: 10078,
		 LongTermTracking: 10079,
	},
}

};

