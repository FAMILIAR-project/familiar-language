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
			 APrioriKnowledge: {
				 BehaviourLibrary: 10007,
				 Scenery3DModel: 10008,
				 DeploymentArchitecture: {
					 ClientDefined: 10009,
					 Sensor: {
						 SensorProperty: {
								 MonoSensor: 10010 ,
								 MultiSensor: 10011 ,
						},
						 SensorView: {
								 SideView: 10012 ,
								 TopView: 10013 ,
								 NormalView: 10014 ,
						},
						 Camera: {
							 Resolution: 10015,
							 CameraType: {
									 BlackAndWhite: 10016 ,
									 Color: 10017 ,
									 Infrared: 10018 ,
							},
							 FramesPerSecond: {
									 HighFrameRate: {
										 VingtCinqFrSec: 10019,
									},
									 LowFrameRate: 10020 ,
									 IntermediateFrameRate: 10021 ,
							},
							 DepthOfField: 10022,
							 FieldOfView: {
									 Large: 10023 ,
									 Narrow: 10024 ,
							},
						},
					},
					 Server: 10025,
					 ProgrammerDefined: 10026,
				},
			},
			 Environment: {
				 LightingType: {
						 ArtificialLight: 10027 ,
						 NaturalLight: 10028 ,
				},
				 Noise: {
					 LightingVariation: {
							 Flashes: 10029 ,
							 HeadLight: 10030 ,
							 Shadows: 10031 ,
					},
					 BackgroundMovement: {
							 SandOrDust: 10032 ,
							 SeaOceanWaves: 10033 ,
							 VegetationMovement: 10034 ,
					},
				},
				 LightingConditions: {
						 Indoors: 10035 ,
						 Outdoors: {
							 TimeOfDay: {
									 Day: 10036 ,
									 Night: 10037 ,
							},
						},
				},
			},
		},
		 ObjectOfInterest: {
			 Cardinality: {
					 SingleObject: 10038 ,
					 GroupOfObjects: 10039 ,
			},
			 Sort: {
					 Natural: {
						 Person: 10040,
					},
					 Manufactured: {
						 Vehicle: 10041,
					},
			},
			 Mobility: {
					 Movable: {
							 ExternallyPropelled: 10042 ,
							 SelfPropelled: 10043 ,
					},
					 Immovable: 10044 ,
			},
		},
	},
	 VSPlatform: {
		 FrameToFrameTracking: 10045,
		 LongTermTracking: 10046,
		 ScenarioRecognition: 10047,
		 ClassificationModule: {
			 Contour: 10048,
			 Merge: {
					 FusionGravityCenter: 10049 ,
					 FusionStandard: 10050 ,
			},
			 ClassificationStandard: {
				 Density: 10051,
			},
			 Model: {
					 ModelType: {
							 OmegaModel: 10052 ,
							 ThreeDModel: 10053 ,
					},
					 Ellipse: 10054 ,
					 Shape: 10055 ,
					 Parallelepiped: 10056 ,
					 GravityCenter: 10057 ,
			},
			 Split: {
					 SplitStandard: 10058 ,
					 SplitGravityCenter: 10059 ,
			},
		},
		 Clustering: {
				 DistanceRGBAnd3DClustering: 10060 ,
				 FastClustering: 10061 ,
				 KMeansClustering: 10062 ,
		},
		 ImageAcquisition: 10063,
		 ReferenceImageUpdating: 10064,
		 SegmentationModule: {
			 TraversalAlgorithm: {
				 TraversalParameters: {
						 WithWindow: 10065 ,
						 WithMask: 10066 ,
						 Fast: 10067 ,
				},
				 GridStep: {
						 HighGridStep: 10068 ,
						 LowGridStep: 10069 ,
				},
			},
			 KernelFunction: {
				 ObjectAspect: {
						 Edge: 10070 ,
						 Region: 10071 ,
				},
				 ShadowElimination: 10072,
				 ColorType: {
						 GreyLevel: 10073 ,
						 SystemColor: 10074 ,
				},
			},
			 Threshold: {
					 LowThreshold: 10075 ,
					 MidThreshold: 10076 ,
					 HighThreshold: 10077 ,
			},
			 SegmFineTune: 10078,
		},
		 TrackingDataFusion: 10079,
	},
}

};

