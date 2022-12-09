/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT } from '@/shared/date/filters';
import EcommerceService from '@/entities/ecommerce/ecommerce.service';
import { Ecommerce } from '@/shared/model/ecommerce.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('Ecommerce Service', () => {
    let service: EcommerceService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new EcommerceService();
      currentDate = new Date();
      elemDefault = new Ecommerce(0, 'AAAAAAA', 0, false, 0, 0, currentDate, 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            saleDate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Ecommerce', async () => {
        const returnedFromService = Object.assign(
          {
            userID: 'BBBBBB',
            productQuantity: 1,
            confirmation: true,
            invoiceCode: 1,
            invoicePrice: 1,
            saleDate: dayjs(currentDate).format(DATE_FORMAT),
            productsAvailable: 1,
            status: true,
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            saleDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Ecommerce', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
